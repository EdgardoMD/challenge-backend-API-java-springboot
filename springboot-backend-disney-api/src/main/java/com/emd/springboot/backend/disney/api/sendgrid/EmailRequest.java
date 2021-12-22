package com.emd.springboot.backend.disney.api.sendgrid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@AllArgsConstructor
@ToString
public class EmailRequest {
	
	private String to;
	private String subject;
	private String body;

}
