package com.moviestore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.TokenDto;
import com.moviestore.controller.form.LoginForm;
import com.moviestore.security.TokenService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> authentication(@RequestBody @Valid LoginForm login){
		UsernamePasswordAuthenticationToken user = login.convert();		
		try {
			Authentication authentication = authenticationManager.authenticate(user);
			String token = tokenService.createToken(authentication);
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));
		} catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
