package com.chika.server.payload.responses.user;

import com.chika.server.Formatter;
import com.chika.server.models.account.User;
import com.chika.server.models.user.UserInfo;
import com.chika.server.services.RoleService;
import lombok.Data;

@Data
public class UserInfoResponse {

    private Long id;
    private String createAt;
    private String avatar;
    private String name;
    private String birthday;
    private String address;
    private String email;
    private String phone;
    private String role;

    public UserInfoResponse(User user, UserInfo userInfo) {
        this.id = user.getId();
        this.createAt = Formatter.formatTimeDay(user.getCreatedAt());
        this.avatar = userInfo.getAvatar();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthday = userInfo.getBirthday();
        this.address = userInfo.getAddress();
        this.role = RoleService.getHighestRole(user.getRoles())
                    .toString().replace("ROLE_", "");
    }
}
