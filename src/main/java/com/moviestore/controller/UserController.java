package com.moviestore.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.moviestore.controller.dto.UserDto;
import com.moviestore.controller.form.UserForm;
import com.moviestore.model.User;
import com.moviestore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final String PATH_ID = "/{id}";

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid UserForm userForm, // BindingResult bindingResult,
			UriComponentsBuilder uriBuilder) {
		HttpHeaders headers = new HttpHeaders();
		// if (!bindingResult.hasErrors()) {
		User user = userService.createNewUser(userForm);
		UserDto userDto = new UserDto(user);
		URI uri = uriBuilder.path(PATH_ID).buildAndExpand(user.getId()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<UserDto>(userDto, headers, HttpStatus.CREATED);
		// }else {
		// return new ResponseEntity<UserDto>(headers,HttpStatus.BAD_REQUEST);
		// }

	}

}
