package com.moviestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity 
@Table(name = "users")
public class User {//implements GrantedAuthority {

	//private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Required name field.")
	@Column(name = "name", length = 80, nullable = false)
	private String name;
	
	@NotBlank(message = "Required e-mail field.")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotBlank(message = "Required password field.")
	@Column(name = "password", nullable = false)
	private String password;
		
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
