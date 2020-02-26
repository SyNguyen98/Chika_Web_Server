package com.chika.server.payload.responses;

import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import com.chika.server.services.RoleService;
import lombok.Data;

@Data
public class UserResponse {

    private String name;
    private String email;
    private RoleName role;

    public UserResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = RoleService.getHighestRole(user.getRoles());
    }
}
