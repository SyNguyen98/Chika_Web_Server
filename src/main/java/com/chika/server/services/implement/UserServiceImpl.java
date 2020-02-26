package com.chika.server.services.implement;

import com.chika.server.exception.AppException;
import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.user.AdminInfo;
import com.chika.server.models.account.Role;
import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import com.chika.server.models.user.UserInfo;
import com.chika.server.repositories.user.AdminInfoRepository;
import com.chika.server.repositories.account.RoleRepository;
import com.chika.server.repositories.account.UserRepository;
import com.chika.server.repositories.user.UserInfoRepository;
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
 * @since 13-02-2020
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final AdminInfoRepository adminInfoRepository;

    private final UserInfoRepository userInfoRepository;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                           AdminInfoRepository adminInfoRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.adminInfoRepository = adminInfoRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User", "phone", phone));
    }

    @Override
    public List<User> getAllByRole(Role role) {
        return userRepository.findAllByRoles(role);
    }

    @Override
    public AdminInfo getAdminInfo(Long userId) {
        return adminInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Info", "user's id", userId));
    }

    @Override
    public UserInfo getUserInfo(Long userId) {
        return userInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Info", "user's id", userId));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public User updateUser(Long userId, String name, String phone, String email) {
        User user = getById(userId);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public Boolean changePassword(Long userId, String oldPassword, String newPassword) {
        User user = getById(userId);
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
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Boolean isExistByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public Boolean isExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
