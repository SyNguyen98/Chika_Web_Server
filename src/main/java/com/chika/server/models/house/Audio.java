package com.chika.server.models.house;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "audio")
@Data
public class Audio {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;
}
