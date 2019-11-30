package com.chika.server.models.house;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class RemoteIr {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @NotBlank
    private String moduleId;

    private String roomId;

    private Long userId;

    @OneToMany(mappedBy = "remoteId")
    private List<IrValue> irValues;

    public RemoteIr() {}
    public RemoteIr(String name, String moduleId, String roomId, Long userId) {
        this.name = name;
        this.moduleId = moduleId;
        this.roomId = roomId;
        this.userId = userId;
    }
}
