package com.istrategies.security.service;

import com.istrategies.security.entity.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> getByUsername(String user);
}
