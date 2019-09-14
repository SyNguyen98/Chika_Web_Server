package com.chika.server.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 16-8-2019
 */
@Data
public class LoginRequest {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
