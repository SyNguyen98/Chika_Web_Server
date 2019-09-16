package com.chika.server.services;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String resetPassword(String token, String newPassword);

    String forgetPassword(String email);
}
