package com.chika.server.payload.responses.products;

import com.chika.server.models.product.ButtonWifi;
import com.chika.server.models.product.SwitchWifi;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SwitchResponse {

    private String id;
    private String name;
    private List<ButtonWifi> buttonWifis;

    public SwitchResponse(SwitchWifi _switchWifi) {
        this.id = _switchWifi.getId();
        this.name = _switchWifi.getName();
        this.buttonWifis = _switchWifi.getButtonWifis();
    }

    public SwitchResponse(SwitchWifi _switchWifi, List<ButtonWifi> buttonWifis) {
        this.id = _switchWifi.getId();
        this.name = _switchWifi.getName();
        this.buttonWifis = buttonWifis;
    }
}
