package com.chika.server.controllers;

import com.chika.server.models.account.User;
import com.chika.server.payload.requests.PasswordRequest;
import com.chika.server.payload.responses.UserResponse;
import com.chika.server.security.CurrentUser;
import com.chika.server.security.UserPrincipal;
import com.chika.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 04-10-2019
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserResponse getUserByUsername(@CurrentUser UserPrincipal currentUser) {
        return new UserResponse(userService.getUserByUsername(currentUser.getUsername()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(new UserResponse(user));
        }
        return userResponses;
    }

    @PutMapping
    public UserResponse updateUser(@CurrentUser UserPrincipal currentUser, @RequestBody User user) {
        return new UserResponse(userService.updateUser(user.getUsername(), user.getName(), user.getEmail()));
    }

    @PutMapping("/change-password")
    public Boolean changePassword(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody PasswordRequest passwordRequest) {
        return userService.changePassword(currentUser.getUsername(),
                passwordRequest.getOldPassword(), passwordRequest.getNewPassword());
    }

    @PutMapping("/forget-password")
    public String forgetPassword(@RequestParam("email") String email) {
        return userService.forgetPassword(email);
    }

    @PostMapping(value = "/reset-password",
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String resetPassword(@RequestParam(name = "token") String token, @RequestParam(name = "password") String password) {
        return userService.resetPassword(token, password);
    }
}
