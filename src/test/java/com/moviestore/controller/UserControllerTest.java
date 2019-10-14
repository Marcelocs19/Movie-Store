package com.moviestore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviestore.MovieStoreApplication;
import com.moviestore.controller.form.UserForm;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieStoreApplication.class)
@WithMockUser(roles = "admin")
@AutoConfigureMockMvc
public class UserControllerTest {

	private static final String PATH_CREATE_NEW_USER = "/users";
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void testCreateUserSucess() throws Exception {
		UserForm newUser = new UserForm();	
		newUser.setEmail("allan@gmail.com");
		newUser.setName("Allan");
		newUser.setPassword("abcd");
		ObjectMapper mapper = new ObjectMapper();
		String newUserAsJSON = mapper.writeValueAsString(newUser);
		mockMvc.perform(post(PATH_CREATE_NEW_USER)
				.content(newUserAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());
	}
	
}
