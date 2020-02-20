package com.chika.server.models.product;

import com.chika.server.models.audit.DateAudit;
import com.chika.server.models.house.Device;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Connect to table Switch in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-02-2020
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Switch extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private Integer type;

    private Long userId;

    @OneToMany(mappedBy = "switchId", fetch = FetchType.LAZY)
    private List<Device> devices;

    public Switch(String name, int type) {
        this.name = name;
        this.type = type;
    }
}
