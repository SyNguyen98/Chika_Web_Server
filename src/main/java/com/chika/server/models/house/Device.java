package com.chika.server.models.house;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Connect to table Device in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-12-2019
 */
@Entity
@Data
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String name;

    private int state;

    private String roomId;

    @NotBlank
    private String switchId;

    public Device(String name, int state, String roomId, String switchId) {
        this.name = name;
        this.state = state;
        this.roomId = roomId;
        this.switchId = switchId;
    }
}
