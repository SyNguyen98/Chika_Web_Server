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
 * @since 19-03-2020
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

    private int state;

    @NotBlank
    private String roomId;

    @NotBlank
    private String buttonId;

    public Device(String logo, String name, String roomId, String buttonId) {
        this.logo = logo;
        this.name = name;
        this.roomId = roomId;
        this.buttonId = buttonId;
        this.state = 0;
    }
}
