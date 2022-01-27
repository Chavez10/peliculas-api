package com.istrategies.security.dto;

import javax.validation.constraints.NotBlank;

public class UserLogin {

    @NotBlank(message = "El usuario no puede ser vacio")
    private String username;
    @NotBlank(message = "La contraseña es requirida")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
