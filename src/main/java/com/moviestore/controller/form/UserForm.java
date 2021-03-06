package com.moviestore.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserForm {
		
	@NotBlank(message = "Required e-mail field.")
	@Email
	private String email;
	
	@NotBlank(message = "Required e-mail field.")
	private String password;
	
	@NotBlank(message = "Required name field.")
	@Size(min = 1, max = 5, message = "Tamanho minímo {2} e tamanho maximo {1}")
	private String name;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
		
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getPassword() {
		return password;
	}
	
	
	
}
