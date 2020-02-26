package com.chika.server.payload.responses;

import com.chika.server.models.user.AdminInfo;
import com.chika.server.models.account.User;
import lombok.Data;

@Data
public class AdminInfoResponse {

    private String avatar;
    private String name;
    private String birthday;
    private String address;
    private String email;
    private String phone;
    private String employeeId;
    private String function;
    private String department;

    public AdminInfoResponse(User user, AdminInfo adminInfo) {
        this.avatar = adminInfo.getAvatar();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthday = adminInfo.getBirthday();
        this.address = adminInfo.getAddress();
        this.employeeId = adminInfo.getEmployeeId();
        this.function = adminInfo.getFunction();
        this.department = adminInfo.getDepartment();
    }
}
