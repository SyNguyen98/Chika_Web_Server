package com.chika.server.payload.responses;

import lombok.Data;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 16-8-2019
 */
@Data
public class AuthenticationResponse {

    private String tokenType = "Bearer";
    private String accessToken;

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
