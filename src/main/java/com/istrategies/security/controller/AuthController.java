package com.istrategies.security.controller;

import com.istrategies.security.dto.JwtDto;
import com.istrategies.security.dto.NewUser;
import com.istrategies.security.dto.UserLogin;
import com.istrategies.security.entity.Role;
import com.istrategies.security.entity.User;
import com.istrategies.security.enums.NameRole;
import com.istrategies.security.jwt.JwtProvider;
import com.istrategies.security.service.RoleService;
import com.istrategies.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/new-user")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser user){
        Map<String, Object> response = new HashMap<>();
        User userdb = new User(
                user.getUsername(), user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                user.getStatus()
        );
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRole(NameRole.ROLE_USER).get());
        if (user.getRoles().contains("admin")){
            roles.add(roleService.getByRole(NameRole.ROLE_ADMIN).get());
        }
        userdb.setRoles(roles);
        userService.saveUser(userdb);
        response.put("user saved", userdb.getUsername());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin user){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), user.getPassword()
                        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.genereteToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, user.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
