package com.moviestore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.controller.form.UserForm;
import com.moviestore.model.Movie;
import com.moviestore.model.Rent;
import com.moviestore.model.Status;
import com.moviestore.model.User;
import com.moviestore.repository.MovieRepository;
import com.moviestore.repository.RentRepository;
import com.moviestore.repository.UserRepository;

@Service
public class RentService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private UserRepository userRepository;


	public List<MovieDto> rentMovie(Long id, UserForm userForm) {

		Optional<Movie> movie = movieRepository.findById(id);
		if (movie.isPresent()) {
			if (movie.get().getCurrentQuantity() == 0) {
				movie.get().setAvailable(false);
			} else {
				Optional<User> user = userRepository.findByEmail(userForm.getEmail());
				List<Rent> listRent = rentRepository.findByMovie_idAndStatusAndUser_Email(id, Status.RENTED,
						user.get().getEmail());
				if (listRent.isEmpty()) {
					movie.get().setCurrentQuantity(movie.get().getCurrentQuantity() - 1);
					Rent rent = new Rent(user.get(), movie.get(), Status.RENTED);
					rentRepository.saveAndFlush(rent);
				}
			}
		}
		return MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(0));
	}

	public List<MovieDto> returnMovie(Long id, UserForm userForm) {
		Optional<Rent> rent = rentRepository.findById(id);
		Optional<User> user = userRepository.findByEmail(userForm.getEmail());
		if (rent.isPresent() && user.isPresent()) {
			if (rent.get().getUser().getId() == user.get().getId()) {
				if (rent.get().getMovie().getCurrentQuantity() < rent.get().getMovie().getTotalAmount()) {
					rent.get().setDate_return(LocalDateTime.now());
					rent.get().setStatus(Status.RETURNED);
					rent.get().getMovie().setAvailable(true);
					rent.get().getMovie().setCurrentQuantity(rent.get().getMovie().getCurrentQuantity() + 1);
				}
			}
		}
		return MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(0));
	}

}
