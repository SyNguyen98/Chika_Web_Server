package com.chika.server.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 16-8-2019
 */
@Data
@AllArgsConstructor
public class ApiResponse {

    private Boolean success;
    private String message;
}
