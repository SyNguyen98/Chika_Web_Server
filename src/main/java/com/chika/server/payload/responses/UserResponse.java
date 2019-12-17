package com.chika.server.payload.responses;

import com.chika.server.Formatter;
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
        this.createAt = Formatter.formatTime(user.getCreatedAt().getTime());
        this.role = user.getRoles().iterator().next().getName();
    }
}
