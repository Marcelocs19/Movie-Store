package com.moviestore.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity 
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Required name field.")
	@Column(name = "name", length = 80, nullable = false)
	private String name;
	
	@NotBlank(message = "Required e-mail field.")
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Required password field.")
	@Column(name = "password", nullable = false)
	private String password;
		
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Rent> rented  = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	public User() {
		
	}

	public User(@NotBlank(message = "Required name field.") String name,
			@NotBlank(message = "Required e-mail field.") @Email String email,
			@NotBlank(message = "Required password field.") String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
		
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
		this.name = name.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}
		
	public List<Rent> getRented() {
		return rented;
	}

	public void setRented(List<Rent> rented) {
		this.rented = rented;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
}
