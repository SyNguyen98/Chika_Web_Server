package com.chika.server.models.product;

import com.chika.server.models.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Connect to table Switch Rf in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 25-02-2020
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

    @NotNull
    private Integer type;

    @NotBlank
    private Long channel;

    private Long userId;

    @OneToMany(mappedBy = "switchRfId", fetch = FetchType.LAZY)
    private List<ButtonRf> buttonRfs;

    public SwitchRf(String name, int type, Long channel) {
        this.name = name;
        this.type = type;
        this.channel = channel;
    }
}