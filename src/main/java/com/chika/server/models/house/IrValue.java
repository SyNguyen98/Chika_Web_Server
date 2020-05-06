package com.chika.server.models.house;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Connect to table Ir Value in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 06-05-2020
 */
@Entity
@Data
@NoArgsConstructor
public class IrValue {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String device;

    private String function;

    private String protocol;

    private Integer nbit;

    private String value;

    private String state;

    @Column(length = 600, columnDefinition = "TEXT")
    private String rawData;

    public IrValue(String device, String function, String protocol, Integer nbit, String value, String state, String rawData) {
        this.device = device;
        this.function = function;
        this.protocol = protocol;
        this.nbit = nbit;
        this.value = value;
        this.state = state;
        this.rawData = rawData;
    }
}
