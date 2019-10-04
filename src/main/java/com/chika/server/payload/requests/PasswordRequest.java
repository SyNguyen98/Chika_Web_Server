package com.chika.server.payload.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordRequest {

    @NotBlank
    private String oldPassword;

    @NotBlank
    @Size(min = 6)
    private String newPassword;
}
