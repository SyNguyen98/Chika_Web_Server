package com.chika.server.services.implement;

import com.chika.server.exception.AppException;
import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.account.AdminInfo;
import com.chika.server.models.account.Role;
import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import com.chika.server.repositories.account.AdminInfoRepository;
import com.chika.server.repositories.account.RoleRepository;
import com.chika.server.repositories.account.UserRepository;
import com.chika.server.services.EmailService;
import com.chika.server.services.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * CRUD function for User
 * @author Sy Nguyen
 * @version 1.0
 * @since 08-02-2019
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final AdminInfoRepository adminInfoRepository;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AdminInfoRepository adminInfoRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.adminInfoRepository = adminInfoRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @Override
    public AdminInfo getAdminInfo(Long userId) {
        return adminInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Info", "user's id", userId));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String username, String name, String email) {
        User user = getByUsername(username);
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public User updateRole(String username) {
        User user = getByUsername(username);
        Role userRole = roleRepository.findByName(RoleName.ROLE_HOME_MASTER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        return user;
    }

    @Override
    public Boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = getByUsername(username);
        if (BCrypt.checkpw(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return true;
        }
        return false;
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByPassword(token)
                .orElseThrow(() -> new ResourceNotFoundException("User", "token", token));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void forgetPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        emailService.sendHtmlMail(user.getEmail(), user.getPassword());
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public Boolean isExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean isExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
