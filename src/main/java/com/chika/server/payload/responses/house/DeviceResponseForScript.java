package com.chika.server.payload.responses.house;

import com.chika.server.models.house.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DeviceResponseForScript {

    private String roomName;
    private List<DeviceResponse> devices;

    public DeviceResponseForScript(String roomName) {
        this.roomName = roomName;
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(new DeviceResponse(device));
    }

    static class DeviceResponse {

        private final String id;
        private final String name;
        private final String topic;
        private final String type;
        private final int switchButton;

        public DeviceResponse(Device device) {
            this.id = device.getId();
            this.name = device.getName();
            this.topic = device.getTopic();
            this.type = device.getType();
            this.switchButton = device.getSwitchButton();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getTopic() {
            return topic;
        }

        public String getType() {
            return type;
        }

        public int getSwitchButton() {
            return switchButton;
        }
    }
}
