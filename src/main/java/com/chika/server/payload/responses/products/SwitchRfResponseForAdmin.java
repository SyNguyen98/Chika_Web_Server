package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.ButtonRf;
import com.chika.server.models.product.SwitchRf;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SwitchRfResponseForAdmin {

    private String id;
    private String day;
    private Integer type;
    private Long channel;
    private Long userId;
    private List<String> buttonId;

    public SwitchRfResponseForAdmin(SwitchRf switchRf) {
        this.id = switchRf.getId();
        this.day = Formatter.formatDay(switchRf.getCreatedAt());
        this.type = switchRf.getType();
        this.channel = switchRf.getChannel();
        this.userId = switchRf.getUserId();
        this.buttonId = switchRf.getButtonRfs().stream()
                .map(ButtonRf::getId)
                .collect(Collectors.toList());
    }

    public SwitchRfResponseForAdmin(SwitchRf switchRf, List<ButtonRf> buttonRfs) {
        this.id = switchRf.getId();
        this.day = Formatter.formatDay(switchRf.getCreatedAt());
        this.type = switchRf.getType();
        this.channel = switchRf.getChannel();
        this.userId = switchRf.getUserId();
        this.buttonId = buttonRfs.stream()
                .map(ButtonRf::getId)
                .collect(Collectors.toList());
    }
}
