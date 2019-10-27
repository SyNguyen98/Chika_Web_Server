package com.chika.server.models.house;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Connect to table device with time in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 09-08-2019
 */
@Entity
@Data
@NoArgsConstructor
public class DeviceHistory {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String deviceId;

    @NotNull
    private int state;

    @NotNull
    private Timestamp time;

    public DeviceHistory(String deviceId, int state, Timestamp time) {
        this.deviceId = deviceId;
        this.state = state;
        this.time = time;
    }
}
