package com.chika.server.models.device;

import com.chika.server.models.house.Device;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Connect to table Switch in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 23-11-2019
 */
@Entity
@Table(name = "switches")
public class Switch {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToMany(mappedBy = "switchId", fetch = FetchType.LAZY)
    private List<Device> devices;

    public Switch() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
