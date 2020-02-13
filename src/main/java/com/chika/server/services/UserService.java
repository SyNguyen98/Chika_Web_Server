package com.chika.server.services;

import com.chika.server.models.account.Role;
import com.chika.server.models.user.AdminInfo;
import com.chika.server.models.account.User;
import com.chika.server.models.user.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getByUsername(String username);

    List<User> getAllByRole(Role role);

    AdminInfo getAdminInfo(Long userId);

    User saveUser(User user);

    UserInfo saveUserInfo(UserInfo userInfo);

    User updateUser(String username, String name, String email);

    User updateRole(String username);

    Boolean changePassword(String username, String oldPassword, String newPassword);

    void resetPassword(String token, String newPassword);

    void forgetPassword(String email);

    void deleteUser(String username);

    Boolean isExistByUsername(String username);

    Boolean isExistByEmail(String email);
}
