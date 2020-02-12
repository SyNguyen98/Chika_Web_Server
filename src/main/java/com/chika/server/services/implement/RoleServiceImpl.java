package com.chika.server.services.implement;

import com.chika.server.exception.AppException;
import com.chika.server.models.account.Role;
import com.chika.server.models.account.RoleName;
import com.chika.server.repositories.account.RoleRepository;
import com.chika.server.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(RoleName roleName) {
        return roleRepository.findByName(RoleName.ROLE_HOME_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
    }
}
