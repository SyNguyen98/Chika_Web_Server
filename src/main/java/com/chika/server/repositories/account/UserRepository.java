package com.chika.server.repositories.account;

import com.chika.server.models.account.Role;
import com.chika.server.models.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 26-02-2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneOrEmail(String phone, String email);

    Optional<User> findByPassword(String password);

    List<User> findAllByRoles(Role role);

    void deleteByPhone(String phone);

    Boolean existsByPhone(String phone);

    Boolean existsByEmail(String email);
}

