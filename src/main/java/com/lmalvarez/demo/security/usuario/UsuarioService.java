package com.lmalvarez.demo.security.usuario;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmalvarez.demo.exception.CustomConflictException;
import com.lmalvarez.demo.security.dto.JwtDto;
import com.lmalvarez.demo.security.dto.LoginUsuario;
import com.lmalvarez.demo.security.dto.NuevoUsuario;
import com.lmalvarez.demo.security.jwt.JwtProvider;
import com.lmalvarez.demo.security.rol.Rol;
import com.lmalvarez.demo.security.rol.RolNombre;
import com.lmalvarez.demo.security.rol.RolService;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RolService rolService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void crearUsuario(@Valid NuevoUsuario nuevoUsuario) {
		if (usuarioRepository.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			throw new CustomConflictException("Nombre de Usuario " + nuevoUsuario.getNombreUsuario() + " ya existe");
		if (usuarioRepository.existsByEmail(nuevoUsuario.getEmail()))
			throw new CustomConflictException("Email " + nuevoUsuario.getEmail() + " ya existe");
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
				nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		if (nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuarioRepository.save(usuario);

	}

	public JwtDto login(@Valid LoginUsuario loginUsuario) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return jwtDto;
	}

	public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
}
