package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.account.User;
import com.chika.server.repositories.account.UserRepository;
import com.chika.server.services.EmailService;
import com.chika.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.1
 * @since 27-09-2019
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String username, String name, String email) {
        User user = getUserByUsername(username);
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public Boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = getUserByUsername(username);
        if (BCrypt.checkpw(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return true;
        }
        return false;
    }

    @Override
    public String resetPassword(String token, String newPassword) {
        User user = userRepository.findByPassword(token)
                .orElseThrow(() -> new ResourceNotFoundException("User", "token", token));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Reset password successfully";
    }

    @Override
    public String forgetPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        return emailService.sendHtmlMail(user.getEmail(), user.getPassword());
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
