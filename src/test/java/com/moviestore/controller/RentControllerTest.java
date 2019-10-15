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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviestore.MovieStoreApplication;
import com.moviestore.controller.dto.MovieDto;
import com.moviestore.controller.form.LoginForm;
import com.moviestore.controller.form.UserForm;
import com.moviestore.model.Movie;
import com.moviestore.model.Rent;
import com.moviestore.model.Status;
import com.moviestore.model.User;
import com.moviestore.repository.RentRepository;
import com.moviestore.repository.UserRepository;
import com.moviestore.security.TokenService;
import com.moviestore.service.MovieService;
import com.moviestore.service.RentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieStoreApplication.class)
@AutoConfigureMockMvc
public class RentControllerTest {

	private static final String PATH_RENT_MOVIE = "/rent/1";
	private static final String PATH_RETURN_MOVIE = "/rent/return/4";
	private static final String PATH_LOGIN = "/login";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthenticationManager authenticationManager;

	@MockBean
	private MovieService movieServices;

	@MockBean
	private RentService rentService;

	@MockBean
	private TokenService tokenService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private RentRepository rentRepository;

	private static final Long TEST_ID_MOVIE1 = 1L;
	private static final Long TEST_ID_MOVIE2 = 2L;

	private Movie movieTest1;
	private Movie movieTest2;

	private User userTest1;

	private List<Movie> movies;

	private List<User> users;

	private List<MovieDto> moviesDto;

	private List<Rent> rents;

	private Rent rent;

	@Before
	public void setup() {

		movies = new ArrayList<>();

		users = new ArrayList<>();

		rents = new ArrayList<>();

		moviesDto = new ArrayList<>();

		userTest1 = new User();
		userTest1.setId(3L);
		userTest1.setEmail("allan@gmail.com");
		userTest1.setName("Allan");
		userTest1.setPassword("abcd");
		userTest1.isEnabled();

		users.add(userTest1);

		movieTest1 = new Movie();
		movieTest1.setId(TEST_ID_MOVIE1);
		movieTest1.setAvailable(true);
		movieTest1.setTotalAmount(3);
		movieTest1.setCurrentQuantity(2);
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

		movies.add(movieTest1);

		rent = new Rent();
		rent.setId(4L);
		rent.setUser(userTest1);
		rent.setMovie(movieTest1);
		rent.setStatus(Status.RENTED);

		rents.add(rent);

	}

	@Test
	public void testRentMovieSucess() throws Exception {
		given(this.movieServices.listAllAvailableMovies()).willReturn(moviesDto);
		given(this.userRepository.findAll()).willReturn(users);

		UserForm userForm = new UserForm();
		userForm.setEmail(userTest1.getEmail());
		userForm.setName(userTest1.getName());
		userForm.setPassword(userTest1.getPassword());

		LoginForm login = new LoginForm();
		login.setEmail(userTest1.getEmail());
		login.setPassword(userTest1.getPassword());

		ObjectMapper mapper = new ObjectMapper();
		String userLogin = mapper.writeValueAsString(userForm);

		UsernamePasswordAuthenticationToken aux = login.convert();

		Authentication authentication = authenticationManager.authenticate(aux);

		String token = tokenService.createToken(authentication);

		mockMvc.perform(post(PATH_RENT_MOVIE).header(PATH_LOGIN, userLogin).content(token)
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void testReturnMovieSucess() throws Exception {
		given(this.rentRepository.findAll()).willReturn(rents);

		UserForm userForm = new UserForm();
		userForm.setEmail(userTest1.getEmail());
		userForm.setName(userTest1.getName());
		userForm.setPassword(userTest1.getPassword());

		LoginForm login = new LoginForm();
		login.setEmail(userTest1.getEmail());
		login.setPassword(userTest1.getPassword());

		ObjectMapper mapper = new ObjectMapper();
		String userLogin = mapper.writeValueAsString(userForm);

		UsernamePasswordAuthenticationToken aux = login.convert();

		Authentication authentication = authenticationManager.authenticate(aux);

		String token = tokenService.createToken(authentication);

		this.mockMvc
				.perform(post(PATH_RETURN_MOVIE).header(PATH_LOGIN, userLogin).content(token)
						.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

}
