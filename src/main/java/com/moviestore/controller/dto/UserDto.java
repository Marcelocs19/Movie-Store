package com.moviestore.controller.dto;

import com.moviestore.model.User;

public class UserDto {

	private Long id;
	private String name;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
		
}
