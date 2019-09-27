package com.chika.server.controllers;

import com.chika.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/forget-password")
    public String forgetPassword(@RequestParam String email) {
        return userService.forgetPassword(email);
    }

    @PostMapping(value = "/reset-password",
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String resetPassword(@RequestParam(name = "token") String token,
                              @RequestParam(name = "password") String password) {
        return userService.resetPassword(token, password);
    }
}
