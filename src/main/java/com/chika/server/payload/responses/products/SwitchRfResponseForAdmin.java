package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.SwitchRf;
import lombok.Data;

@Data
public class SwitchRfResponseForAdmin {

    private String id;
    private String day;
    private String type;
    private Long channel;
    private Long userId;

    public SwitchRfResponseForAdmin(SwitchRf switchRf) {
        this.id = switchRf.getId();
        this.day = Formatter.formatDay(switchRf.getCreatedAt());
        this.type = switchRf.getType();
        this.channel = switchRf.getChannel();
        this.userId = switchRf.getUserId();
    }
}
