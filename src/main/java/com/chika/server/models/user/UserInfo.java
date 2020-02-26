package com.chika.server.models.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String avatar;

    private String birthday;

    private String address;

    public UserInfo(Long userId) {
        this.userId = userId;
        this.avatar = "";
        this.birthday = "";
        this.address = "";
    }
}
