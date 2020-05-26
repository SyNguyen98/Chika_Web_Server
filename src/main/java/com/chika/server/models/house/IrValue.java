package com.chika.server.models.house;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Connect to table Ir Value in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-05-2020
 */
@Entity
@NoArgsConstructor
public class IrValue {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String device;

    @NotBlank
    private String protocol;

    @NotNull
    private Integer size;

    @OneToMany(mappedBy = "irValueId")
    private List<IrData> irData;

    public IrValue(String device, String protocol, Integer size) {
        this.device = device;
        this.protocol = protocol;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<IrData> getIrData() {
        return irData;
    }

    public void setIrData(List<IrData> irData) {
        this.irData = irData;
    }
}
