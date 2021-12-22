package com.emd.springboot.backend.disney.api.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class LoginRequest {

	@NotBlank
	@Size(min = 3, max = 20)
	private String nombreUsuario;

	@NotBlank
	@Size(min = 4, max = 50)
	private String password;

}
