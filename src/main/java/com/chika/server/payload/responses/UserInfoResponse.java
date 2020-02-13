package com.chika.server.payload.responses;

import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import com.chika.server.models.user.UserInfo;
import com.chika.server.services.RoleService;
import lombok.Data;

@Data
public class UserInfoResponse {

    private String avatar;
    private String name;
    private String username;
    private String birthday;
    private String address;
    private String email;
    private String phone;
    private RoleName role;

    public UserInfoResponse(User user, UserInfo userInfo) {
        this.avatar = userInfo.getAvatar();
        this.name = user.getName();
        this.username = user.getUsername();
        this.birthday = userInfo.getBirthday();
        this.address = userInfo.getAddress();
        this.email = user.getEmail();
        this.phone = userInfo.getPhone();
        this.role = RoleService.getHighestRole(user.getRoles());
    }
}
