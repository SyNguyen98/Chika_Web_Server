package com.chika.server.models.house;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Connect to table Script Device in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 09-06-2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptDevice {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String deviceId;
    private String name;
    private String type;
    private String topic;
    private Boolean state;
    private Integer switchButton;

    private String scriptId;

    public ScriptDevice(String deviceId, String name, String type, String topic,
                        Boolean state, Integer switchButton, String scriptId) {
        this.deviceId = deviceId;
        this.name = name;
        this.type = type;
        this.topic = topic;
        this.state = state;
        this.switchButton = switchButton;
        this.scriptId = scriptId;
    }
}
