package com.moviestore.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviestore.MovieStoreApplication;
import com.moviestore.controller.form.LoginForm;
import com.moviestore.model.User;
import com.moviestore.repository.UserRepository;
import com.moviestore.security.TokenService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieStoreApplication.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

	private static final String PATH_LOGIN = "/login";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private TokenService tokenService;
		
	private static final Long TEST_ID_USER = 1L;
	
	private User user;
	
	private List<User> users;
	
	@Before
	public void setup() {
		
		users = new ArrayList<>();
		
		user = new User();
		user.setId(TEST_ID_USER);
		user.setEmail("allan@gmail.com");
		user.setName("Allan");
		user.setPassword("1234");
		
		users.add(user);
		
	}
	
	@Test
	public void testLoginSucess() throws Exception {
		given(this.userRepository.findAll()).willReturn(users);	
		
				
		LoginForm login = new LoginForm();
		login.setEmail(user.getEmail());
		login.setPassword(user.getPassword());
		
		ObjectMapper mapper = new ObjectMapper();
		String token = mapper.writeValueAsString(login);		
		
		mockMvc.perform(post(PATH_LOGIN)
				.content(token).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testLoginFailure() throws Exception {
		given(this.userRepository.findAll()).willReturn(users);	
		
		LoginForm login = new LoginForm();
		login.setEmail("");
		login.setPassword(user.getPassword());
		
		ObjectMapper mapper = new ObjectMapper();
		String token = mapper.writeValueAsString(login);
		
		mockMvc.perform(post(PATH_LOGIN)
				.content(token).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}
	
	
	
	
}
