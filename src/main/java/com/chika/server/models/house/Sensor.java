package com.chika.server.models.house;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Connect to table Sensor in database
 * @author Sy Nguyen
 * @version 1.1
 * @since 08-09-2019
 */
@Entity
@Table(name = "Sensor")
@Data
public class Sensor {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private double data;

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + data;
    }
}
