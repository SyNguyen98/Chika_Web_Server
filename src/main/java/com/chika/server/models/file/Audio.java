package com.chika.server.models.file;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Audio {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @Lob
    @NotNull
    private byte[] data;

    public Audio(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
