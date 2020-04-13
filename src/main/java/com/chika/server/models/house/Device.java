package com.chika.server.models.house;

import com.chika.server.models.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Connect to table Device in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 13-04-2020
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Device extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String logo;

    @NotBlank
    private String name;

    private Boolean state;

    @NotBlank
    private String roomId;

    @NotBlank
    private String type;

    @NotBlank
    private String topic;

    private int switchButton;

    public Device(String logo, String name, String roomId, String type, String topic, int switchButton) {
        this.logo = logo;
        this.name = name;
        this.roomId = roomId;
        this.type = type;
        this.topic = topic;
        this.switchButton = switchButton;
        this.state = false;
    }
}
