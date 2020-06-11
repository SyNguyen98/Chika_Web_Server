package com.chika.server.models.house;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Connect to table Script in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 11-06-2019
 */
@Entity
@NoArgsConstructor
public class Script {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String logo;
    private String name;
    private String time;
    private String days;
    private Long userId;

    @OneToMany(mappedBy = "scriptId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScriptDevice> devices;

    public Script(String logo, String name, String time, String days, Long userId) {
        this.logo = logo;
        this.name = name;
        this.time = time;
        this.days = days;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ScriptDevice> getDevices() {
        return devices;
    }

    public void setDevices(List<ScriptDevice> devices) {
        this.devices = devices;
    }
}
