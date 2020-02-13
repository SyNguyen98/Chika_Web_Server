package com.chika.server.models.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String avatar;

    private String phone;

    private String address;

    public UserInfo() {}
    public UserInfo(Long userId, String avatar, String phone, String address) {
        this.userId = userId;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
    }
}
