package com.emd.springboot.backend.disney.api.sendgrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sendgrid.Response;

@RestController
@RequestMapping("/send-email")
public class EmailSenderController {
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@PostMapping
	public ResponseEntity<?> enviarEmail(@RequestBody EmailRequest request){
		
		System.out.println(request);
		
		Response response=emailSenderService.sendEmail(request);
		
		if(response.getStatusCode()==200||response.getStatusCode()==202)
			return new ResponseEntity<>("Email enviado exitosamente",HttpStatus.OK);
		return new ResponseEntity<>("Fallo el envio del email",HttpStatus.NOT_FOUND);
		
	}

}
