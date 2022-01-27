package com.istrategies.security.config;

import com.istrategies.security.entity.Role;
import com.istrategies.security.enums.NameRole;
import com.istrategies.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRole implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        /*Role admin = new Role(NameRole.ROLE_ADMIN, true);
        Role user = new Role(NameRole.ROLE_USER, true);
        roleService.saveRole(admin);
        roleService.saveRole(user);*/
    }
}
