package com.chika.server.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageResponse {

    private String name;
    private String uri;
    private String type;
}
