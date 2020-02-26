package com.chika.server.controllers;

import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import com.chika.server.models.product.Product;
import com.chika.server.models.user.UserInfo;
import com.chika.server.payload.requests.SignInRequest;
import com.chika.server.payload.requests.SignUpRequest;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.AuthenticationResponse;
import com.chika.server.security.JwtTokenProvider;
import com.chika.server.services.RoleService;
import com.chika.server.services.UserService;
import com.chika.server.services.product.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * To control authentication from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-02-2020
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    private final UserService userService;
    private final RoleService roleService;

    private final SwitchWifiService switchWifiService;
    private final SwitchRfService switchRfService;
    private final ModuleIrService moduleIrService;
    private final HomeCenterService homeCenterService;
    private final SensorService sensorService;

    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider,
                          UserService userService, RoleService roleService, SwitchWifiService switchWifiService, SwitchRfService switchRfService,
                          ModuleIrService moduleIrService, HomeCenterService homeCenterService, SensorService sensorService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.roleService = roleService;
        this.switchWifiService = switchWifiService;
        this.switchRfService = switchRfService;
        this.moduleIrService = moduleIrService;
        this.homeCenterService = homeCenterService;
        this.sensorService = sensorService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getPhoneOrEmail(),
                        signInRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        List<Product> products = signUpRequest.getProducts();
        if (!products.isEmpty()) {
            String productName = checkListProduct(products);
            if (!productName.equals("")) {
                return new ResponseEntity<>(new ApiResponse(false, productName + " already have owner!"),
                        HttpStatus.BAD_REQUEST);
            }

            if(userService.isExistByPhone(signUpRequest.getPhone())) {
                return new ResponseEntity<>(new ApiResponse(false, "Phone number is already taken!"),
                        HttpStatus.BAD_REQUEST);
            }
            if(userService.isExistByEmail(signUpRequest.getEmail())) {
                return new ResponseEntity<>(new ApiResponse(false, "Email address already in use!"),
                        HttpStatus.BAD_REQUEST);
            }

            User user = new User(signUpRequest.getName(), signUpRequest.getPhone(), signUpRequest.getEmail(),
                    passwordEncoder.encode(signUpRequest.getPassword()));

            user.setRoles(Stream.of(roleService.getRoleByName(RoleName.ROLE_HOME_USER),
                    roleService.getRoleByName(RoleName.ROLE_HOME_MASTER))
                    .collect(Collectors.toSet()));

            user = userService.saveUser(user);
            userService.saveUserInfo(new UserInfo(user.getId()));
            updateProductWithUserId(user.getId(), products);

            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "You need to own at least 1 Chika product to create an account!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    String checkListProduct(List<Product> products) {
        for (Product product : products) {
            switch (product.getName()) {
                case "Switch Wifi": {
                    for (String id : product.getIds()) {
                        if (switchWifiService.hasOwner(id)) {
                            return "Switch Wifi";
                        }
                    }
                    break;
                }
                case "Switch Rf": {
                    for (String id : product.getIds()) {
                        if (switchRfService.hasOwner(id)) {
                            return "Switch Rf";
                        }
                    }
                    break;
                }
                case "Module Ir": {
                    for (String id : product.getIds()) {
                        if (moduleIrService.hasOwner(id)) {
                            return "Module Ir";
                        }
                    }
                    break;
                }
                case "Home Center": {
                    for (String id : product.getIds()) {
                        if (homeCenterService.hasOwner(id)) {
                            return "Home Center";
                        }
                    }
                    break;
                }
                case "Sensor": {
                    for (String id : product.getIds()) {
                        if (sensorService.hasOwner(id)) {
                            return "Sensor";
                        }
                    }
                    break;
                }
            }
        }
        return "";
    }

    void updateProductWithUserId(Long userId, List<Product> products) {
        products.forEach(product -> {
            switch (product.getName()) {
                case "Switch Wifi": {
                    product.getIds().forEach(id -> switchWifiService.updateUser(id, userId));
                    break;
                }
                case "Switch Rf": {
                    product.getIds().forEach(id -> switchRfService.updateUser(id, userId));
                    break;
                }
                case "Module Ir": {
                    product.getIds().forEach(id -> moduleIrService.updateUser(id, userId));
                    break;
                }
                case "Home Center": {
                    product.getIds().forEach(id -> homeCenterService.updateUser(id, userId));
                    break;
                }
                case "Sensor": {
                    product.getIds().forEach(id -> sensorService.updateUser(id, userId));
                    break;
                }
            }
        });
    }
}