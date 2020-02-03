package com.chika.server.payload.responses;

import lombok.Data;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@Data
public class AuthenticationResponse {

    private String tokenType = "Bearer";
    private String accessToken;
    private String userRole;

    public AuthenticationResponse(String accessToken, String userRole) {
        this.accessToken = accessToken;
        this.userRole = userRole;
    }
}
