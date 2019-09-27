package com.chika.server.models.house;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Connect to table Sensor in database
 * @author Sy Nguyen
 * @version 1.1
 * @since 08-09-2019
 */
@Entity
@Table(name = "sensors")
@Data
public class Sensor {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private String name;

    @Column
    private double data;

    private Long userId;
}
