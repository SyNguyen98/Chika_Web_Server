package com.chika.server.models.house;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Connect to table Device in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 19-10-2019
 */
@Entity
@Table(name = "devices")
@Data
public class Device {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private int state;

    private String roomId;
    
    private String switchId;

    private Long userId;

    public Device(String name, int state, String roomId, String switchId, Long userId) {
        this.name = name;
        this.state = state;
        this.roomId = roomId;
        this.switchId = switchId;
        this.userId = userId;
    }
}
