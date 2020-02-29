package com.chika.server.payload.responses.user;

import com.chika.server.models.account.User;
import com.chika.server.services.RoleService;
import lombok.Data;

@Data
public class UserResponse {

    private String name;
    private String phone;
    private String email;
    private String role;

    public UserResponse(User user) {
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.role = RoleService.getHighestRole(user.getRoles())
                    .toString().replace("ROLE_", "");
    }
}
