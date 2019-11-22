package com.chika.server.models.house;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class IrValue {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String value;

    @NotBlank
    private String remoteIrId;

    public IrValue() {}
    public IrValue(String name, String value, String remoteIrId) {
        this.name = name;
        this.value = value;
        this.remoteIrId = remoteIrId;
    }
}
