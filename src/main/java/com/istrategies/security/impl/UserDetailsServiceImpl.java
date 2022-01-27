package com.istrategies.security.impl;

import com.istrategies.security.entity.MainUser;
import com.istrategies.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserImpl user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User _user = user.getByUsername(username).get();
        return MainUser.build(_user);
    }
}
