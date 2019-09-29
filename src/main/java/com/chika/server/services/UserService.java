package com.chika.server.services;

import com.chika.server.models.account.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUserByUsername(String username);

    User saveUser(User user);

    User updateUser(Long id, String name, String email);

    String resetPassword(String token, String newPassword);

    String forgetPassword(String email);

    Boolean isExistByUsername(String username);

    Boolean isExistByEmail(String email);
}
