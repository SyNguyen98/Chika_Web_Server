package com.chika.server.payload.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RemoteIrRequest {

    @NotBlank
    private String name;

    @NotNull
    private int numOfButton;

    @NotBlank
    private String roomId;

    @NotBlank
    private String moduleId;
}
