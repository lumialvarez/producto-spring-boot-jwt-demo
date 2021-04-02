package com.lmalvarez.demo.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.demo.security.dto.JwtDto;
import com.lmalvarez.demo.security.dto.LoginUsuario;
import com.lmalvarez.demo.security.dto.NuevoUsuario;
import com.lmalvarez.demo.security.usuario.UsuarioService;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/nuevo")
    public void crearUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario){
    	usuarioService.crearUsuario(nuevoUsuario);
    }

    @PostMapping("/login")
    public JwtDto login(@Valid @RequestBody LoginUsuario loginUsuario){
    	return usuarioService.login(loginUsuario);
    }
}
