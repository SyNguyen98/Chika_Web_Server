package com.chika.server.models.house;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Connect to table Ir Value in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-12-2019
 */
@Entity
@Data
@NoArgsConstructor
public class IrValue {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String value;

    @NotBlank
    private String remoteId;

    public IrValue(String name, String value, String remoteId) {
        this.name = name;
        this.value = value;
        this.remoteId = remoteId;
    }
}
