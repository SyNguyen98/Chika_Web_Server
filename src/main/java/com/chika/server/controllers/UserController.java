package com.chika.server.controllers;

import com.chika.server.models.account.User;
import com.chika.server.payload.responses.UserResponse;
import com.chika.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 27-09-2019
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public UserResponse getUser(@PathVariable String username) {
        return new UserResponse(userService.getUserByUsername(username));
    }

    @RolesAllowed("ADMIN")
    @PostMapping
    public UserResponse saveUser(@RequestBody User user) {
        return new UserResponse(userService.saveUser(user));
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody User user) {
        return new UserResponse(userService.updateUser(user.getId(), user.getName(), user.getEmail()));
    }

    @PutMapping("/forget-password/{email}")
    public String forgetPassword(@PathVariable String email) {
        return userService.forgetPassword(email);
    }

    @PostMapping(value = "/reset-password",
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String resetPassword(@RequestParam(name = "token") String token,
                              @RequestParam(name = "password") String password) {
        return userService.resetPassword(token, password);
    }
}
