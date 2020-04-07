package com.chika.server.payload.responses.house;

import com.chika.server.Formatter;
import com.chika.server.models.house.Device;
import lombok.Data;

@Data
public class DeviceResponse {

    private String id;
    private String logo;
    private String name;
    private String productId;
    private String topic;
    private String createAt;

    public DeviceResponse(Device device) {
        this.id = device.getId();
        this.logo = device.getLogo();
        this.name = device.getName();
        this.productId = device.getProductId();
        this.topic = device.getTopic();
        this.createAt = Formatter.formatDay(device.getCreatedAt());
    }
}
