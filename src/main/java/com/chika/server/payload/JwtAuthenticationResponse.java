package com.chika.server.payload;

import lombok.Data;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 16-8-2019
 */
@Data
public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
