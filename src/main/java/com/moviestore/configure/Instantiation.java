package com.moviestore.configure;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.moviestore.model.Movie;
import com.moviestore.model.Rent;
import com.moviestore.model.Status;
import com.moviestore.model.User;
import com.moviestore.repository.MovieRepository;
import com.moviestore.repository.RentRepository;
import com.moviestore.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RentRepository rentRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();

		//new User(name,email,password(hash = "1234"))
		User user1 = new User("userMaria", "maria@gmail.com",
				"$2a$10$ThN1p9Y1VrM3RvcPBkT1qeWCqsegZGrWNdnsK1CLYOUjSf5iELlWm");
		User user2 = new User("userPaul", "paulB@gmail.com",
				"$2a$10$ThN1p9Y1VrM3RvcPBkT1qeWCqsegZGrWNdnsK1CLYOUjSf5iELlWm");
		User user3 = new User("userBob", "bob@gmail.com",
				"$2a$10$ThN1p9Y1VrM3RvcPBkT1qeWCqsegZGrWNdnsK1CLYOUjSf5iELlWm");
		User user4 = new User("userJohn", "johnD@gmail.com",
				"$2a$10$ThN1p9Y1VrM3RvcPBkT1qeWCqsegZGrWNdnsK1CLYOUjSf5iELlWm");
		User user5 = new User("userAriel", "arial@gmail.com",
				"$2a$10$ThN1p9Y1VrM3RvcPBkT1qeWCqsegZGrWNdnsK1CLYOUjSf5iELlWm");

		userRepository.saveAll(Arrays.asList(user1,user2,user3,user4,user5));

		movieRepository.deleteAll();

		Movie movie1 = new Movie("Fantastic Beasts and Where to Find Them", 5, 5, "J.K. Rowling", true);
		Movie movie2 = new Movie("Fantastic Beasts: The Crimes of Grindelwald", 5, 5, "J.K. Rowling", true);
		Movie movie3 = new Movie("Star Wars: Episode VII - The Force Awakens", 5, 5, "J. J. Abrams", true);
		Movie movie4 = new Movie("Star Wars: The Last Jedi", 3, 3, "Rian Johnson", true);
		Movie movie5 = new Movie("The Lord of the Rings: The Fellowship of the Ring", 5, 5, "Peter Jackson", true);
		Movie movie6 = new Movie("The Lord of the Rings: The Two Towers", 5, 5, "Peter Jackson", true);

		movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6));
//
//		rentRepository.deleteAll();
//
//		Rent rent1 = new Rent(user1, movie4, Status.RENTED);
//		Rent rent2 = new Rent(user1, movie7, Status.RENTED);
//
//		Rent rent3 = new Rent(user4, movie4, Status.RENTED);
//		Rent rent4 = new Rent(user4, movie7, Status.RENTED);
//
//		rentRepository.saveAll(Arrays.asList(rent1, rent2, rent3, rent4));

	}

}
