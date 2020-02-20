package com.chika.server.models.product;

import com.chika.server.models.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Connect to table Home Center in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-02-2020
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class HomeCenter extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String name;

    private Long userId;

    public HomeCenter() {
        this.name = "CA-HC";
    }
}
