package com.istrategies.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class NewUser {

    @NotBlank(message = "Username cannot be empty")
    @NotEmpty(message = "Username is required")
    private String username;
    @NotBlank(message = "Email cannot be empty")
    @NotEmpty(message = "Email is required")
    @Email(message = "The email does not have a valid format.")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @NotEmpty(message = "Password is required")
    private String password;
    @NotNull(message = "Status is required")
    private Boolean status;

    private Set<String> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
