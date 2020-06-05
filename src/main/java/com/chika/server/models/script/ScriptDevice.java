package com.chika.server.models.script;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Connect to table Script Device in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 05-06-2020
 */
@Entity
@Data
@NoArgsConstructor
public class ScriptDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;

    private Long scriptId;
    
    public ScriptDevice(String deviceId, Long scriptId) {
        this.deviceId = deviceId;
        this.scriptId = scriptId;
    }
}
