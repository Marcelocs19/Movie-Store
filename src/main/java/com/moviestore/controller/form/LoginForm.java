package com.moviestore.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotBlank(message = "Required e-mail field.")
	@Email
	private String email;
	
	@NotBlank(message = "Required e-mail field.")
	private String password;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(email,password);
	}
	
}
