package com.moviestore.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.moviestore.MovieStoreApplication;
import com.moviestore.controller.dto.MovieDto;
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
	
	private static final String PATH_LIST_AVAILABLE_MOVIES = "/movies";
	private static final String PATH_SEARCH_MOVIES = "/movies/search";
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieService movieServices;
	
			
	private Movie movieTest1;
	private Movie movieTest2;
	private Movie movieTest3;
	
	private List<Movie> movies;

	private List<MovieDto> moviesDto;
	private List<MovieDto> moviesDtoSearch;
	
	@Before
	public void setup() {
		
		movies = new ArrayList<>();
		
		moviesDto = new ArrayList<>();
		moviesDtoSearch = new ArrayList<>();
		
		movieTest1 = new Movie();
		movieTest1.setId(TEST_ID_MOVIE1);
		movieTest1.setAvailable(true);
		movieTest1.setTotalAmount(3);
		movieTest1.setCurrentQuantity(3);
		movieTest1.setDirector_name("James Cameron");
		movieTest1.setTitle("Titanic");
			
		movies.add(movieTest1);		
		
		movieTest2 = new Movie();
		movieTest2.setId(TEST_ID_MOVIE2);
		movieTest2.setAvailable(true);
		movieTest2.setTotalAmount(3);
		movieTest2.setCurrentQuantity(3);
		movieTest2.setDirector_name("Jon Favreau");
		movieTest2.setTitle("Iron Man");
				
		movies.add(movieTest2);
		
		movieTest3 = new Movie();
		movieTest3.setId(TEST_ID_MOVIE3);
		movieTest3.setAvailable(true);
		movieTest3.setTotalAmount(3);
		movieTest3.setCurrentQuantity(3);
		movieTest3.setDirector_name("James McTeigue");
		movieTest3.setTitle("V for Vendetta");		
		
		movies.add(movieTest3);
		
	}
	
	@Test
	public void testListAvailableMoviesSucess() throws Exception {
		moviesDto.addAll(MovieDto.convertMoviesToDto(movies));
		given(this.movieServices.listAllAvailableMovies()).willReturn(moviesDto);
		mockMvc.perform(get(PATH_LIST_AVAILABLE_MOVIES)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(TEST_ID_MOVIE1))
				.andExpect(jsonPath("$.[0].title").value("Titanic"))
				.andExpect(jsonPath("$.[1].id").value(TEST_ID_MOVIE2))
				.andExpect(jsonPath("$.[1].title").value("Iron Man"))
				.andExpect(jsonPath("$.[2].id").value(TEST_ID_MOVIE3))
				.andExpect(jsonPath("$.[2].title").value("V for Vendetta"));
	}
	
	@Test
	public void testSearchMovieSucess() throws Exception {
		moviesDtoSearch.addAll(MovieDto.convertMoviesToDto((Arrays.asList(movieTest1))));
		given(this.movieServices.searchMovie("Titanic")).willReturn(moviesDtoSearch);
		mockMvc.perform(post(PATH_SEARCH_MOVIES).param("title","Titanic")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(TEST_ID_MOVIE1))
				.andExpect(jsonPath("$.[0].title").value("Titanic"));
	}
	
	@Test
	public void testListAvailableMoviesNotFound() throws Exception {
		moviesDto.clear();
		given(this.movieServices.listAllAvailableMovies()).willReturn(moviesDto);
		mockMvc.perform(get(PATH_LIST_AVAILABLE_MOVIES)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testSearchMovieNotFound() throws Exception {
		moviesDtoSearch.clear();
		given(this.movieServices.searchMovie("Titanic")).willReturn(moviesDtoSearch);
		mockMvc.perform(post(PATH_SEARCH_MOVIES).param("title","Titanic")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
}
