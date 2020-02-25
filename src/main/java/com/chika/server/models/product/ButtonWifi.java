package com.chika.server.models.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Connect to table Button Wifi in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 25-02-2020
 */
@Entity
@Data
@NoArgsConstructor
public class ButtonWifi {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private int state;

    private String roomId;

    @NotBlank
    private String switchWifiId;

    public ButtonWifi(String switchWifiId) {
        this.name = "";
        this.state = 0;
        this.roomId = "";
        this.switchWifiId = switchWifiId;
    }
}
