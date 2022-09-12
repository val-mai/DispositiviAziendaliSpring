package com.epicode.dispositivi.auth.login;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.dispositivi.auth.jwt.JwtResponse;
import com.epicode.dispositivi.auth.jwt.JwtUtils;
import com.epicode.dispositivi.auth.users.UserDetailsImpl;





@RestController
@CrossOrigin(origins="*")
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
		
		UsernamePasswordAuthenticationToken usrNameAuth = new UsernamePasswordAuthenticationToken( 
				request.getUserName(), 
				request.getPassword()
		);
		Authentication authentication = authManager.authenticate(usrNameAuth);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities()
								.stream()
								.map(item -> item.getAuthority())
								.collect(Collectors.toList());
		
		JwtResponse jwtresp = new JwtResponse(
				jwt, 
				userDetails.getId(), 
				userDetails.getUsername(),
				roles
			);
		
		return ResponseEntity.ok(jwtresp);
		
	}
}
