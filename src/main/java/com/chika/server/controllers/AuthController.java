package com.chika.server.controllers;

import com.chika.server.exception.AppException;
import com.chika.server.models.account.Role;
import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.AuthenticationResponse;
import com.chika.server.payload.requests.SignInRequest;
import com.chika.server.payload.requests.SignUpRequest;
import com.chika.server.repositories.account.RoleRepository;
import com.chika.server.security.JwtTokenProvider;
import com.chika.server.services.RoleService;
import com.chika.server.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

/**
 * To control authentication from client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-18-2019
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    private final RoleService roleService;

    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, UserService userService, RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsernameOrEmail(),
                        signInRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userService.isExistByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userService.isExistByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                            passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleService.getRoleByName(RoleName.ROLE_HOME_USER);

        user.setRoles(Collections.singleton(userRole));

        user = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}