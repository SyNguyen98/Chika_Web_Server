package com.chika.server.services;

import com.chika.server.models.account.Role;
import com.chika.server.models.account.RoleName;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {

    Role getRoleByName(RoleName roleName);

    static RoleName getHighestRole(Set<Role> roles) {
        RoleName highestRole = RoleName.ROLE_HOME_USER;
        for (Role role : roles) {
            if (role.getName().compareTo(highestRole) < 0) {
                highestRole = role.getName();
            }
        }
        return highestRole;
    }
}
