package com.emd.springboot.backend.disney.api.payload.response;

import java.util.List;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Integer id;
	private String nombreUsuario;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, Integer id, String nombreUsuario, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return nombreUsuario;
	}

	public void setUsername(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public List<String> getRoles() {
		return roles;
	}

}
