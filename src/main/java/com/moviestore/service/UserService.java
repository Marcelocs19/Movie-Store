package com.moviestore.service;

import org.springframework.stereotype.Service;

import com.moviestore.controller.form.UserForm;
import com.moviestore.model.User;
import com.moviestore.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User createNewUser(UserForm userForm) {
		User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPassword());
		userRepository.saveAndFlush(user);
		return user;
	}
}
