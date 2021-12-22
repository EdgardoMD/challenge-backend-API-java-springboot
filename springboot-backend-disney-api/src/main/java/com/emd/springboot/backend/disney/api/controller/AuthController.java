package com.emd.springboot.backend.disney.api.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emd.springboot.backend.disney.api.model.entity.Rol;
import com.emd.springboot.backend.disney.api.model.entity.Usuario;
import com.emd.springboot.backend.disney.api.model.entity.NombreRol;
import com.emd.springboot.backend.disney.api.model.repository.IRolRepository;
import com.emd.springboot.backend.disney.api.model.repository.IUsuarioRepository;
import com.emd.springboot.backend.disney.api.payload.request.LoginRequest;
import com.emd.springboot.backend.disney.api.payload.request.SignupRequest;
import com.emd.springboot.backend.disney.api.payload.response.JwtResponse;
import com.emd.springboot.backend.disney.api.payload.response.MessageResponse;
import com.emd.springboot.backend.disney.api.security.jwt.JwtUtils;
import com.emd.springboot.backend.disney.api.security.services.UsuarioImpl;
import com.emd.springboot.backend.disney.api.sendgrid.EmailRequest;
import com.emd.springboot.backend.disney.api.sendgrid.EmailSenderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUsuarioRepository userRepository;

	@Autowired
	private IRolRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		System.out.println("Entro al login " + loginRequest);
		Authentication authentication = 
				authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getNombreUsuario(), loginRequest.getPassword()));
		
		
		
		

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UsuarioImpl userDetails = (UsuarioImpl) authentication.getPrincipal();	
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	@PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		System.out.println(signUpRequest);
		
	    if (userRepository.existsByNombreUsuario(signUpRequest.getNombreUsuario())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Nombre de usuario ya existe!"));
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email ya existe!"));
	    }

	    // Crea una nueva cuenta de usuario
	    Usuario user = new Usuario(signUpRequest.getNombreUsuario(),
	               encoder.encode(signUpRequest.getPassword()),
	               signUpRequest.getEmail());

	    Set<String> strRoles = signUpRequest.getRoles();
	    Set<Rol> roles = new HashSet<>();

	    if (strRoles == null) {
	        Rol userRole = roleRepository.findByNombre(NombreRol.ROLE_USER)
	            .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
	        roles.add(userRole);
	      } else {
	        strRoles.forEach(role -> {
	          switch (role) {
	          case "admin":
	            Rol adminRole = roleRepository.findByNombre(NombreRol.ROLE_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
	            roles.add(adminRole);

	            break;
	          
	          default:
	            Rol userRole = roleRepository.findByNombre(NombreRol.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
	            roles.add(userRole);
	          }
	        });
	      }

	    user.setRoles(roles);
	    userRepository.save(user);
	    
	    EmailRequest request = new EmailRequest(user.getEmail(), 
	    		"Bienvenido a nuestra App", 
	    		user.getNombreUsuario().toString() + "Le damos la bienvenida y agradecemos su participacion");
	    
	    emailSenderService.sendEmail(request);
	    

	    return ResponseEntity.ok(new MessageResponse("El usuario se ha registrado exitosmente!"));
	  }

}
