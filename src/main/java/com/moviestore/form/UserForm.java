package com.moviestore.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserForm {
		
	@NotBlank(message = "Required e-mail field.")
	@Email
	private String email;
	
	@NotBlank(message = "Required e-mail field.")
	private String password;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
