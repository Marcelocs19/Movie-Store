package com.moviestore.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.form.LoginForm;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@PostMapping
	public ResponseEntity<?> authentication(@RequestBody @Valid LoginForm login){
		
		return ResponseEntity.ok().build();
	}
}
