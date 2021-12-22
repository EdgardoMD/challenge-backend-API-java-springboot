package com.emd.springboot.backend.disney.api.sendgrid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendGridConfig {
	
	@Value("${SEND_GRID_KEY}")
	private String sendGridKey;
	
	@Bean
	public SendGrid getSendGrid() {
		return new SendGrid(sendGridKey);
	}

}
