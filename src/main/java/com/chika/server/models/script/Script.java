package com.chika.server.models.script;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Connect to table Script in database
 * @author Sy Nguyen
 * @version 1.0
 * @since 20-12-2019
 */
@Entity
@Data
@NoArgsConstructor
public class Script {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String time;

    @OneToMany(mappedBy = "scriptId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScriptDevice> devices;

    public Script(String name, String time) {
        this.name = name;
        this.time = time;
    }
}
