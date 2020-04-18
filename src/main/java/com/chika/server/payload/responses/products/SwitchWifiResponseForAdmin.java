package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.SwitchWifi;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SwitchWifiResponseForAdmin {

    private String id;
    private String day;
    private String type;
    private Long userId;

    public SwitchWifiResponseForAdmin(SwitchWifi switchWifi) {
        this.id = switchWifi.getId();
        this.day = Formatter.formatDay(switchWifi.getCreatedAt());
        this.type = switchWifi.getType();
        this.userId = switchWifi.getUserId();
    }
}
