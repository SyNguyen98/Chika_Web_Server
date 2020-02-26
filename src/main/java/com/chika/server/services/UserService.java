package com.chika.server.services;

import com.chika.server.models.account.Role;
import com.chika.server.models.user.AdminInfo;
import com.chika.server.models.account.User;
import com.chika.server.models.user.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getById(Long userId);

    User getByPhone(String phone);

    List<User> getAllByRole(Role role);

    AdminInfo getAdminInfo(Long userId);

    UserInfo getUserInfo(Long userId);

    User saveUser(User user);

    UserInfo saveUserInfo(UserInfo userInfo);

    User updateUser(Long userId, String name, String phone, String email);

    Boolean changePassword(Long userId, String oldPassword, String newPassword);

    void resetPassword(String token, String newPassword);

    void forgetPassword(String email);

    void deleteUser(Long userId);

    Boolean isExistByPhone(String phone);

    Boolean isExistByEmail(String email);
}
