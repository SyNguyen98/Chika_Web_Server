package com.chika.server.models.house;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Connect to table Sensor in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 23-11-2019
 */
@Entity
@Table(name = "sensors")
@Data
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

    private Long userId;
}
