package com.moviestore.configure;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.moviestore.model.Movie;
import com.moviestore.repository.MovieRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public void run(String... args) throws Exception {

		movieRepository.deleteAll();

		Movie movie1 = new Movie("Fantastic Beasts and Where to Find Them", 5, 5, "J.K. Rowling", true);
		Movie movie2 = new Movie("Fantastic Beasts: The Crimes of Grindelwald", 5, 5, "J.K. Rowling", true);
		Movie movie3 = new Movie("Star Wars: Episode VII - The Force Awakens", 5, 5, "J. J. Abrams", true);
		Movie movie4 = new Movie("Star Wars: The Last Jedi", 3, 3, "Rian Johnson", true);
		Movie movie5 = new Movie("The Lord of the Rings: The Fellowship of the Ring", 5, 5, "Peter Jackson", true);
		Movie movie6 = new Movie("The Lord of the Rings: The Two Towers", 5, 5, "Peter Jackson", true);

		movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6));


	}

}
