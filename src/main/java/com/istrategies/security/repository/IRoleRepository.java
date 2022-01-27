package com.istrategies.security.repository;

import com.istrategies.security.entity.Role;
import com.istrategies.security.enums.NameRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByNameRole(NameRole nameRole);
    boolean existsByNameRole(NameRole nameRole);
}
