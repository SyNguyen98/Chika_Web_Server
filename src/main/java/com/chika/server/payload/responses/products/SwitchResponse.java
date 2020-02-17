package com.chika.server.payload.responses.products;

import com.chika.server.models.product.Switch;
import com.chika.server.models.house.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SwitchResponse {

    private String id;
    private String name;
    private List<Device> devices;

    public SwitchResponse(Switch _switch) {
        this.id = _switch.getId();
        this.name = _switch.getName();
        this.devices = _switch.getDevices();
    }

    public SwitchResponse(Switch _switch, List<Device> devices) {
        this.id = _switch.getId();
        this.name = _switch.getName();
        this.devices = devices;
    }
}
