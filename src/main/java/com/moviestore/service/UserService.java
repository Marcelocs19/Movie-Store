package com.moviestore.service;

import org.springframework.stereotype.Service;

import com.moviestore.controller.form.UserForm;
import com.moviestore.error.ServiceException;
import com.moviestore.model.User;
import com.moviestore.repository.UserRepository;

@Service
public class UserService {

	private static final String ERROR_CREATE_NEW_USER = "Error create new user.";
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public User createNewUser(UserForm userForm) {
		try {
			User user = new User(userForm.getName(),userForm.getEmail(),userForm.getPassword());
			userRepository.saveAndFlush(user);
			return user;			
		} catch (Exception e) {
			throw new ServiceException(ERROR_CREATE_NEW_USER,e);
		}
	}
}
