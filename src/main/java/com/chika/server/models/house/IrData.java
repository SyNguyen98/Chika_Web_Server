package com.chika.server.models.house;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Connect to table Ir Data in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-05-2020
 */
@Entity
@NoArgsConstructor
public class IrData {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String function;

    @Column(length = 500, columnDefinition = "TEXT")
    @NotBlank
    private String data;

    @NotBlank
    private String irValueId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIrValueId() {
        return irValueId;
    }

    public void setIrValueId(String irValueId) {
        this.irValueId = irValueId;
    }
}
