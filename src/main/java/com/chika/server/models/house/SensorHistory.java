package com.chika.server.models.house;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Connect to table sensor history in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 09-08-2019
 */
@Entity
@Table(name = "sensor_history")
@Data
public class SensorHistory {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "sensor_id")
    private String sensorId;

    @Column(name = "data")
    private double data;

    @Column(name = "time")
    private Timestamp time;

    public SensorHistory(String sensorId, double data, Timestamp time) {
        this.sensorId = sensorId;
        this.data = data;
        this.time = time;
    }
}
