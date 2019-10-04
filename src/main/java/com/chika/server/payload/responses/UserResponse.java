package com.chika.server.payload.responses;

import com.chika.server.ChikaWebServerApplication;
import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String createAt;
    private RoleName role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createAt = ChikaWebServerApplication.formatter.format(user.getCreatedAt());
        this.role = user.getRoles().iterator().next().getName();
    }
}
