package com.chika.server.services;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void resetPassword();

    void forgetPassword(String username);
}
