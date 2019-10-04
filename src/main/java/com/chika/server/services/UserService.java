package com.chika.server.services;

import com.chika.server.models.account.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User saveUser(User user);

    User updateUser(String username, String name, String email);

    Boolean changePassword(String username, String oldPassword, String newPassword);

    String resetPassword(String token, String newPassword);

    String forgetPassword(String email);

    Boolean isExistByUsername(String username);

    Boolean isExistByEmail(String email);
}
