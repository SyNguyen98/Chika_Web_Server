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
 * @since 20-12-2019
 */
@Entity
@Data
@NoArgsConstructor
public class ScriptDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;

    private String value;

    private Long scriptId;
    
    public ScriptDevice(String deviceId, String value, Long scriptId) {
        this.deviceId = deviceId;
        this.value = value;
        this.scriptId = scriptId;
    }
}
