package com.chika.server.payload.requests;

import lombok.Data;

@Data
public class IrRequest {

    private Long userId;
    private int quantity;
}
