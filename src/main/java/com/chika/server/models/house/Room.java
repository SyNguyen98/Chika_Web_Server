package com.chika.server.models.house;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Connect to table Room in database
 * @author Sy Nguyen
 * @version 1.1
 * @since 22-07-2019
 */
@Entity
@Table(name = "Room")
@Data
public class Room {

    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private int floor;

    @Override
    public String toString() {
        return "ID: " + id + "\tRoom: " + name + "\tFloor: " + floor;
    }
}
