package com.istrategies.security.service;

import com.istrategies.security.entity.Role;
import com.istrategies.security.enums.NameRole;

import java.util.Optional;

public interface RoleService {

    Optional<Role> getByRole(NameRole nameRole);
    Role saveRole(Role role);
}
