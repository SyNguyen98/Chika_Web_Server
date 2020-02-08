package com.chika.server.payload.responses;

import com.chika.server.models.account.AdminInfo;
import com.chika.server.models.account.User;
import lombok.Data;

@Data
public class AdminInfoResponse {

    private String avatar;
    private String name;
    private String username;
    private String birthday;
    private String address;
    private String email;
    private String phone;
    private String employeeId;
    private String function;
    private String department;

    public AdminInfoResponse(User user, AdminInfo adminInfo) {
        this.avatar = user.getAvatar();
        this.name = user.getName();
        this.username = user.getUsername();
        this.birthday = adminInfo.getBirthday();
        this.address = adminInfo.getAddress();
        this.email = user.getEmail();
        this.phone = adminInfo.getPhone();
        this.employeeId = adminInfo.getEmployeeId();
        this.function = adminInfo.getFunction();
        this.department = adminInfo.getDepartment();
    }
}
