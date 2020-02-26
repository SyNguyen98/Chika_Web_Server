package com.chika.server.security;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.account.User;
import com.chika.server.repositories.account.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * To authenticate a User or perform various role-based checks
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-02-2020
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either phone or email
        User user = userRepository.findByPhoneOrEmail(phoneOrEmail, phoneOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + phoneOrEmail)
        );
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );
        return UserPrincipal.create(user);
    }
}