package com.moviestore.controller;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.moviestore.controller.dto.UserDto;
import com.moviestore.form.UserForm;
import com.moviestore.model.User;
import com.moviestore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final String ERROR_CREATE_USER = "Error creating a user.";
	
	private static final String PATH_ID = "/{id}";
	
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> createNewUser(@RequestBody UserForm userForm, UriComponentsBuilder uriBuilder)
			throws Exception {
		try {
			User user = userService.createNewUser(userForm);
			URI uri = uriBuilder.path(PATH_ID).buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(uri).body(new UserDto(user));
		} catch (Exception e) {
			throw new Exception(ERROR_CREATE_USER);
		}
	}

}
