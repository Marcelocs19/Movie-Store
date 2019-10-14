package com.moviestore.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.moviestore.MovieStoreApplication;
import com.moviestore.model.Movie;
import com.moviestore.service.MovieService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieStoreApplication.class)
@WithMockUser(roles = "admin")
@AutoConfigureMockMvc
public class MovieControllerTest {
	
	private static final Long TEST_ID_MOVIE1 = 1L;
	private static final Long TEST_ID_MOVIE2 = 2L;
	private static final Long TEST_ID_MOVIE3 = 3L;
	private static final Long TEST_ID_MOVIE4 = 4L;
	
	private static final String PATH_LIST_AVAILABLE_MOVIES = "/movies";
	private static final String PATH_SEARCH_MOVIES = "/movies/search";
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private MovieService movieService;
	
	private Movie movieTest1;
	private Movie movieTest2;
	private Movie movieTest3;
	private Movie movieTest4;
	
	@Before
	public void setup() {
		
		movieTest1 = new Movie();
		movieTest1.setId(TEST_ID_MOVIE1);
		movieTest1.setAvailable(true);
		movieTest1.setTotalAmount(3);
		movieTest1.setCurrentQuantity(3);
		movieTest1.setDirector_name("");
		movieTest1.setTitle("");
		
	}

}
