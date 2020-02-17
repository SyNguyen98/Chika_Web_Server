package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.Switch;
import com.chika.server.models.house.Device;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SwitchResponseForAdmin {

    private String id;
    private String day;
    private Integer type;
    private Long userId;
    private List<String> buttonId;

    public SwitchResponseForAdmin(Switch _switch) {
        this.id = _switch.getId();
        this.day = Formatter.formatTimeDay(_switch.getCreatedAt());
        this.type = _switch.getType();
        this.userId = _switch.getUserId();
        this.buttonId = _switch.getDevices().stream()
                        .map(Device::getId)
                        .collect(Collectors.toList());
    }
}
