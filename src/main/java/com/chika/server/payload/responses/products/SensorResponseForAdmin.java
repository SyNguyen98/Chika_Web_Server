package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.Sensor;
import lombok.Data;

@Data
public class SensorResponseForAdmin {

    private String id;
    private String day;
    private String name;
    private String type;
    private String rfChannel;
    private Long userId;

    public SensorResponseForAdmin(Sensor sensor) {
        this.id = sensor.getId();
        this.day = Formatter.formatDay(sensor.getCreatedAt());
        this.name = sensor.getName();
        this.type = sensor.getType();
        this.rfChannel = sensor.getRfChannel();
        this.userId = sensor.getUserId();
    }
}
