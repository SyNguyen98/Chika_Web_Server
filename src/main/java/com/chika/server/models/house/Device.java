package com.chika.server.models.house;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Connect to table Device in database
 * @author Sy Nguyen
 * @version 1.1
 * @since 08-09-2019
 */
@Entity
@Table(name = "devices")
@Data
public class Device {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private int state;

    @NotBlank
    private String roomId;

    @NotBlank
    private String switchId;

    @NotNull
    private Long userId;
}
