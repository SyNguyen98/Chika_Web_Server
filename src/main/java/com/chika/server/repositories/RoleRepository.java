package com.chika.server.repositories;

import com.chika.server.models.Role;
import com.chika.server.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sy Nguyen
 * @version 1.1
 * @since 16-8-2019
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
