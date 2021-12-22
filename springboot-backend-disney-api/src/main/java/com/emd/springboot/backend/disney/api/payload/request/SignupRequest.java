package com.emd.springboot.backend.disney.api.payload.request;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SignupRequest {
	
	  private String nombreUsuario;

	  private String email;
	  
	  private String password;

	  private Set<String> roles;

}
