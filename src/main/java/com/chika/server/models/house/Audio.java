package com.chika.server.models.house;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "audio")
@Data
public class Audio {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String uri;
}
