package com.chika.server.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceResponse {

    private String id;

    private int state;

    private String audioPath;
}
