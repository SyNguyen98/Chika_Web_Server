package com.chika.server.payload.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-02-2020
 */
@Data
public class SignInRequest {

    @NotBlank
    private String phoneOrEmail;

    @NotBlank
    private String password;
}
