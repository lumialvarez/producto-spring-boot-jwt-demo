package com.lmalvarez.demo.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
    @NotBlank(message = "Campo nombreUsuario es requerido")
    private String nombreUsuario;
    @NotBlank(message = "Campo password es requerido")
    private String password;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
