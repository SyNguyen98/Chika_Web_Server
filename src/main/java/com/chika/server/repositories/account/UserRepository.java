package com.chika.server.repositories.account;

import com.chika.server.models.account.Role;
import com.chika.server.models.account.RoleName;
import com.chika.server.models.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 12-02-2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByPassword(String password);

    List<User> findAllByRoles(Role role);

    void deleteByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

