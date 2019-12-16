package com.chika.server.models.script;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ScriptDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;

    private String value;

    private Long scriptId;

    public ScriptDevice() {}
    public ScriptDevice(String deviceId, String value, Long scriptId) {
        this.deviceId = deviceId;
        this.value = value;
        this.scriptId = scriptId;
    }
}
