package com.chika.server.repositories.account;

import com.chika.server.models.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 22-12-2019
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByPassword(String password);

    void deleteByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

