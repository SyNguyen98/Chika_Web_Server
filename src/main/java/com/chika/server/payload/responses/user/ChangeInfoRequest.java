package com.chika.server.payload.responses.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ChangeInfoRequest {

    @NotBlank
    private String phone;

    @NotBlank
    @Email
    private String email;

    private String birthday;
    private String address;
}
