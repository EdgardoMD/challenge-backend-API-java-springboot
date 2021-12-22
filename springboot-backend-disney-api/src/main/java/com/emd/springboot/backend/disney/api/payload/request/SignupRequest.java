package com.emd.springboot.backend.disney.api.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupRequest {

	@NotBlank
	@Size(min = 3, max = 20)
	private String nombreUsuario;

	@NotBlank
	@Size(max = 30)
	@Email
	private String email;

	@NotBlank
	@Size(min = 4, max = 45)
	private String password;

	private Set<String> roles;

}
