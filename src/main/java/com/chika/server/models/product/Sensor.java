package com.chika.server.models.product;

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
 * Connect to table Sensor in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 15-04-2020
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Sensor extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    private String rfChannel;

    private Long userId;
}
