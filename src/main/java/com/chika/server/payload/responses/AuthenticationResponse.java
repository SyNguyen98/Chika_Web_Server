package com.chika.server.payload.responses;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 02-11-2019
 */
@Data
public class AuthenticationResponse {

    private String tokenType = "Bearer";
    private String accessToken;

    @Value("${app.mqttUsername}")
    private String mqttUsername;
    @Value("${app.mqttPassword}")
    private String mqttPassword;

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
