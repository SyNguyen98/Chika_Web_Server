package com.chika.server.models.house;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Connect to table Sensor in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-12-2019
 */
@Entity
@Data
@NoArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private double data;

    @NotBlank
    private String roomId;

    public Sensor(String name, double data, String roomId) {
        this.name = name;
        this.data = data;
        this.roomId = roomId;
    }
}
