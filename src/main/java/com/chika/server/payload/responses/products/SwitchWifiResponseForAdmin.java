package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.ButtonWifi;
import com.chika.server.models.product.SwitchWifi;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SwitchWifiResponseForAdmin {

    private String id;
    private String day;
    private Integer type;
    private Long userId;
    private List<String> buttonId;

    public SwitchWifiResponseForAdmin(SwitchWifi switchWifi) {
        this.id = switchWifi.getId();
        this.day = Formatter.formatDay(switchWifi.getCreatedAt());
        this.type = switchWifi.getType();
        this.userId = switchWifi.getUserId();
        this.buttonId = switchWifi.getButtonWifis().stream()
                        .map(ButtonWifi::getId)
                        .collect(Collectors.toList());
    }

    public SwitchWifiResponseForAdmin(SwitchWifi switchWifi, List<ButtonWifi> buttonWifis) {
        this.id = switchWifi.getId();
        this.day = Formatter.formatDay(switchWifi.getCreatedAt());
        this.type = switchWifi.getType();
        this.userId = switchWifi.getUserId();
        this.buttonId = buttonWifis.stream()
                        .map(ButtonWifi::getId)
                        .collect(Collectors.toList());
    }
}
