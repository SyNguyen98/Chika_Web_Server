package com.chika.server.models.house;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@NoArgsConstructor
public class SensorHistory {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String sensorId;

    @NotBlank
    private double data;

    @NotBlank
    private Timestamp time;

    public SensorHistory(String sensorId, double data, Timestamp time) {
        this.sensorId = sensorId;
        this.data = data;
        this.time = time;
    }
}
