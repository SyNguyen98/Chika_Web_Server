package com.chika.server.models.house;

import com.chika.server.models.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Connect to table Sensor History in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-12-2019
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class SensorHistory extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String sensorId;

    @NotBlank
    private double data;

    public SensorHistory(String sensorId, double data) {
        this.sensorId = sensorId;
        this.data = data;
    }
}
