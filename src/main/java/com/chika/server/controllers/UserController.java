package com.chika.server.controllers;

import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import com.chika.server.payload.requests.PasswordRequest;
import com.chika.server.payload.responses.AdminInfoResponse;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.UserInfoResponse;
import com.chika.server.payload.responses.UserResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.RoleService;
import com.chika.server.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * To receive User requests from the client
 * @author Sy Nguyen
 * @version 1.0
 * @since 13-02-2020
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public UserResponse getUserByUsername(@CurrentUser UserPrincipal currentUser) {
        return new UserResponse(userService.getByUsername(currentUser.getUsername()));
    }

    @GetMapping("/admin")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public AdminInfoResponse getAdminInfoById(@CurrentUser UserPrincipal currentUser) {
        return new AdminInfoResponse(userService.getByUsername(currentUser.getUsername()),
                userService.getAdminInfo(currentUser.getId()));
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<UserInfoResponse> getAllUsers() {
        return userService.getAllByRole(roleService.getRoleByName(RoleName.ROLE_HOME_USER))
                .stream()
                .map(user -> new UserInfoResponse(user, userService.getUserInfo(user.getId())))
                .collect(Collectors.toList());
    }

    @PutMapping
    public UserResponse updateInfo(@CurrentUser UserPrincipal currentUser, @RequestBody User user) {
        return new UserResponse(userService.updateUser(currentUser.getUsername(), user.getName(), user.getEmail()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{username}/role")
    public UserResponse updateRole(@PathVariable String username) {
        return new UserResponse(userService.updateRole(username));
    }

    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody PasswordRequest passwordRequest) {
        if (userService.changePassword(currentUser.getUsername(),
                passwordRequest.getOldPassword(), passwordRequest.getNewPassword()))  {
            return ResponseEntity.ok(new ApiResponse(true, "Password has been changed"));
        }
        return new ResponseEntity<>(new ApiResponse(false, "Your current password is incorrect"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<?> forgetPassword(@PathVariable String email) {
        userService.forgetPassword(email);
        return ResponseEntity.ok(new ApiResponse(true, "Email has been sent"));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok(new ApiResponse(true, "User has been deleted"));
    }

    @PostMapping(value = "/reset-password",
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> resetPassword(@RequestParam(name = "token") String token, @RequestParam(name = "password") String password) {
        userService.resetPassword(token, password);
        return ResponseEntity.ok(new ApiResponse(true, "Password reset successfully"));
    }
}
