package com.chika.server.models.house;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Connect to table Remote Ir in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-12-2019
 */
@Entity
@Data
@NoArgsConstructor
public class RemoteIr {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @NotBlank
    private String moduleId;

    private String roomId;

    @OneToMany(mappedBy = "remoteId", fetch = FetchType.LAZY)
    private List<IrValue> irValues;

    public RemoteIr(String name, String moduleId, String roomId) {
        this.name = name;
        this.moduleId = moduleId;
        this.roomId = roomId;
    }
}
