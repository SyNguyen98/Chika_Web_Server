package com.chika.server.payload.responses;

import com.chika.server.models.account.User;
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
    private Long userId;

    public AuthenticationResponse(String accessToken, Long userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }
}
