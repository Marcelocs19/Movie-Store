package com.moviestore.configure;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.moviestore.model.Movie;
import com.moviestore.model.User;
import com.moviestore.repository.MovieRepository;
import com.moviestore.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();

		User user1 = new User( "testeA@gmail.com", "testA", "1234");
		User user2 = new User( "testeB@gmail.com", "testB", "1234");
		User user3 = new User( "testeC@gmail.com", "testC", "1234");
		User user4 = new User( "testeD@gmail.com", "testD", "1234");
		User user5 = new User( "testeE@gmail.com", "testE", "1234");
		User user6 = new User( "test@gmail.com", "test", "1234");

		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5,user6));
		
		movieRepository.deleteAll();
		
		Movie movie1 = new Movie("Harry Potter", 5, 5, "TesteA", true);
		Movie movie2 = new Movie("Star Wars", 5, 5, "TesteB", true);
		Movie movie3 = new Movie("Lord of the Rings", 5, 5, "TesteC", true);
		Movie movie4 = new Movie("Batman", 5, 5, "TesteD", true);

		movieRepository.saveAll(Arrays.asList(movie1,movie2,movie3,movie4));

		

	}

}
