package com.emd.springboot.backend.disney.api.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class LoginRequest {

	private String nombreUsuario;

	private String password;

}
