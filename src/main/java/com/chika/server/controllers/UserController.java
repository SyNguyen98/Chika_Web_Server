package com.chika.server.controllers;

import com.chika.server.models.account.User;
import com.chika.server.payload.requests.PasswordRequest;
import com.chika.server.payload.responses.ApiResponse;
import com.chika.server.payload.responses.UserResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * To receive User requests from the client
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponse getUserByUsername(@CurrentUser UserPrincipal currentUser) {
        return new UserResponse(userService.getByUsername(currentUser.getUsername()));
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        List<User> users = userService.getAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(new UserResponse(user));
        }
        return userResponses;
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
