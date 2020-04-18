package com.chika.server.models.product;

import com.chika.server.models.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Connect to table Switch Rf in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 18-04-2020
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class SwitchRf extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotNull
    private Long channel;

    private Long userId;

    public SwitchRf(String name, String type, Long channel) {
        this.name = name;
        this.type = type;
        this.channel = channel;
    }
}