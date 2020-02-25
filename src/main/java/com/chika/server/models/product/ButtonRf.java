package com.chika.server.models.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Connect to table Button Rf in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 25-02-2020
 */
@Entity
@Data
@NoArgsConstructor
public class ButtonRf {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @NotNull
    private int state;

    private String roomId;

    @NotBlank
    private String switchRfId;

    public ButtonRf(String switchRfId) {
        this.name = "";
        this.state = 0;
        this.roomId = "";
        this.switchRfId = switchRfId;
    }
}
