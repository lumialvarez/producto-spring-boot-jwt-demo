package com.lmalvarez.demo.utils;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lmalvarez.demo.producto.Producto;
import com.lmalvarez.demo.producto.ProductoRepository;
import com.lmalvarez.demo.security.rol.Rol;
import com.lmalvarez.demo.security.rol.RolNombre;
import com.lmalvarez.demo.security.rol.RolRepository;
import com.lmalvarez.demo.security.usuario.Usuario;
import com.lmalvarez.demo.security.usuario.UsuarioRepository;

@Configuration
public class InitConfig {
	@Bean
	CommandLineRunner commandLineRunnerStudent(ProductoRepository productoRepository) {
		return args -> {
			Producto p = new Producto("PC", BigDecimal.valueOf(2700000));
			
			productoRepository.saveAll(List.of(p));
		};
	}
	
	@Bean
	CommandLineRunner commandLineRunnerRol(RolRepository rolRepository) {
		return args -> {
			Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
	        Rol rolUser = new Rol(RolNombre.ROLE_USER);
			
	        rolRepository.saveAll(List.of(rolAdmin, rolUser));
		};
	}
	
	@Bean
	CommandLineRunner commandLineRunnerUsuario(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			Usuario admin = new Usuario("admin", "admin", "admin@prueba.com", passwordEncoder.encode("admin"));
			admin.getRoles().add(new Rol(RolNombre.ROLE_ADMIN));
			
			Usuario user = new Usuario("admin", "user", "user@prueba.com", passwordEncoder.encode("user"));
			
			usuarioRepository.saveAll(List.of(admin, user));
		};
	}
}
