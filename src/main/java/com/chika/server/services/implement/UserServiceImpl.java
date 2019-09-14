package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.User;
import com.chika.server.repositories.UserRepository;
import com.chika.server.services.EmailService;
import com.chika.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void resetPassword() {

    }

    @Override
    public void forgetPassword(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        emailService.sendSimpleEmail(user.getEmail(), "123456");
    }
}
