package com.chika.server.repositories.account;

import com.chika.server.models.account.Role;
import com.chika.server.models.account.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sy Nguyen
 * @version 1.1
 * @since 16-08-2019
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
