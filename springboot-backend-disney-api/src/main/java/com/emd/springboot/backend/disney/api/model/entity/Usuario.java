package com.emd.springboot.backend.disney.api.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor @Getter @Setter @ToString
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombreUsuario;
	
	private String password;
	
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"),
				inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles  = new HashSet<>();

	public Usuario(String nombreUsuario, String password, Set<Rol> roles) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.roles = roles;
	}

}
