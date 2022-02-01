package com.istrategies.security.impl;

import com.istrategies.security.entity.User;
import com.istrategies.security.repository.IUserRepository;
import com.istrategies.security.service.UserService;
import com.istrategies.settings.exception.BadRequestException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserImpl implements UserService {

    private final IUserRepository userRepository;

    public UserImpl(IUserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User saveUser(User user) {
        boolean existUsername = userRepository.existsByUsername(user.getUsername());
        boolean existEmail = userRepository.existsByEmail(user.getEmail());

        if (!existUsername && !existEmail){
            return userRepository.save(user);
        }
        throw new BadRequestException("The user or mail already exists. Please try again.");
    }

    @Override
    public int getIdUser(String user) {
        return userRepository.getIdUser(user);
    }

    @Override
    public Optional<User> getByUsername(String user) {
        return userRepository.findByUsername(user);
    }
}
