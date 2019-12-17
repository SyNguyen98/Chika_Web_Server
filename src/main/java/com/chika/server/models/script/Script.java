package com.chika.server.models.script;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Script {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String time;

    @OneToMany(mappedBy = "scriptId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScriptDevice> devices;

    public Script() {}
    public Script(String name, String time) {
        this.name = name;
        this.time = time;
    }
}
