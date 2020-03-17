package com.chika.server.models.house;

import com.chika.server.models.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Connect to table Room in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 17-03-2020
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Room extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String logo;

    @NotBlank
    private String name;

    @NotNull
    private Long userId;

    public Room(String logo, String name, Long userId) {
        this.logo = logo;
        this.name = name;
        this.userId = userId;
    }
}
