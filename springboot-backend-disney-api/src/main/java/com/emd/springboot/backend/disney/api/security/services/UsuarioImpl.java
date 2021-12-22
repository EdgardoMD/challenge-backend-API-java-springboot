package com.emd.springboot.backend.disney.api.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emd.springboot.backend.disney.api.model.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String nombreUsuario;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UsuarioImpl(Integer id, String nombreUsuario, String password, String email,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UsuarioImpl build(Usuario user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre().name()))
				.collect(Collectors.toList());

		return new UsuarioImpl(
				user.getId(), 
				user.getNombreUsuario(), 
				user.getPassword(),
				user.getEmail(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return nombreUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UsuarioImpl user = (UsuarioImpl) o;
		return Objects.equals(id, user.id);
	}

	


}
