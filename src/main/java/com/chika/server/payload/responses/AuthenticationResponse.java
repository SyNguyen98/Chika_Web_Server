package com.chika.server.payload.responses;

import lombok.Data;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 02-11-2019
 */
@Data
public class AuthenticationResponse {

    private String tokenType = "Bearer";
    private String accessToken;

    private String mqttUsername = "chika";
    private String mqttPassword = "2502";

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
