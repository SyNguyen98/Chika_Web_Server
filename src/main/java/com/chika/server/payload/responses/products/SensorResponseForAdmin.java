package com.chika.server.payload.responses.products;

import com.chika.server.Formatter;
import com.chika.server.models.product.Sensor;
import lombok.Data;

@Data
public class SensorResponseForAdmin {

    private String id;
    private String day;
    private Long userId;

    public SensorResponseForAdmin(Sensor sensor) {
        this.id = sensor.getId();
        this.day = Formatter.formatTimeDay(sensor.getCreatedAt());
        this.userId = sensor.getUserId();
    }
}
