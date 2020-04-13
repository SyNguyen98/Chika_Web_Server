package com.chika.server.payload.responses.house;

import com.chika.server.Formatter;
import com.chika.server.models.house.Device;
import lombok.Data;

@Data
public class DeviceResponse {

    private String id;
    private String logo;
    private String name;
    private String type;
    private String topic;
    private int switchButton;
    private String createAt;

    public DeviceResponse(Device device) {
        this.id = device.getId();
        this.logo = device.getLogo();
        this.name = device.getName();
        this.type = device.getType();
        this.topic = device.getTopic();
        this.switchButton = device.getSwitchButton();
        this.createAt = Formatter.formatDay(device.getCreatedAt());
    }
}
