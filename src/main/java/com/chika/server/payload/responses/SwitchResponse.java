package com.chika.server.payload.responses;

import com.chika.server.Formatter;
import com.chika.server.models.device.Switch;
import com.chika.server.models.house.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SwitchResponse {

    private String id;

    private String name;

    private String time;

    private Long userId;

    private List<Device> devices;

    public SwitchResponse(Switch _switch, List<Device> devices) {
        this.id = _switch.getId();
        this.name = _switch.getName();
        this.time = Formatter.formatTime(_switch.getCreatedAt().getTime());
        this.userId = _switch.getUserId();
        this.devices = devices;
    }
}
