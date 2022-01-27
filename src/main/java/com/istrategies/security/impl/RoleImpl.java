package com.istrategies.security.impl;

import com.istrategies.security.entity.Role;
import com.istrategies.security.enums.NameRole;
import com.istrategies.security.repository.IRoleRepository;
import com.istrategies.security.service.RoleService;
import com.istrategies.settings.exception.BadRequestException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleImpl implements RoleService {

    private final IRoleRepository roleRepository;

    public RoleImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> getByRole(NameRole nameRole) {
        return roleRepository.findByNameRole(nameRole);
    }

    @Override
    public Role saveRole(Role role) {
        boolean existRole = roleRepository.existsByNameRole(role.getNameRole());
        if (!existRole){
            return roleRepository.save(role);
        }
        throw new BadRequestException("The role entered already exists. Please try again.");
    }
}
