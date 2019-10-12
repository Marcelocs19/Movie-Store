package com.moviestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.error.ServiceException;
import com.moviestore.form.UserForm;
import com.moviestore.model.Movie;
import com.moviestore.model.Rent;
import com.moviestore.model.Status;
import com.moviestore.model.User;
import com.moviestore.repository.MovieRepository;
import com.moviestore.repository.RentRepository;
import com.moviestore.repository.UserRepository;

@Service
public class MovieService {

	private static final String ERROR_RETURN_MOVIES = "Error return movie.";
	private static final String ERROR_RENT_MOVIES = "Error rent movie.";
	private static final String ERROR_LIST_MOVIES = "Error listing movies.";
	private static final String ERROR_SEARCH_MOVIES = "Error search movie.";
	private static final String ERROR_MOVIES_NOT_FOUND = "No movies found.";

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private UserRepository userRepository;

	public List<MovieDto> listAllAvailableMovies() {
		try {
			List<MovieDto> listAllavailableMovies = MovieDto
					.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(1));
			if (!listAllavailableMovies.isEmpty()) {
				return listAllavailableMovies;
			} else {
				throw new ServiceException(ERROR_MOVIES_NOT_FOUND);
			}

		} catch (Exception e) {
			throw new ServiceException(ERROR_LIST_MOVIES, e);
		}
	}

	public List<MovieDto> searchMovie(String title) {
		try {
			List<MovieDto> search = MovieDto.convertMoviesToDto(movieRepository.findByTitle(title));
			if (!search.isEmpty()) {
				return search;
			} else {
				throw new ServiceException(ERROR_MOVIES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_SEARCH_MOVIES, e);
		}
	}

	public List<MovieDto> rentMovie(Long id, UserForm userForm) {
		try {
			Movie movie = movieRepository.getOne(id);
			if (movie.getCurrentQuantity() == 0) {
				movie.setAvailable(false);
			} else {
				movie.setCurrentQuantity(movie.getCurrentQuantity() - 1);
				User user = userRepository.findByEmail(userForm.getEmail());
				Rent rent = new Rent(user, movie, Status.RENTED);
				List<Rent> listUserRent = user.getRented();
				listUserRent.add(rent);
				user.setRented(listUserRent);
				rentRepository.saveAndFlush(rent);
			}
			List<MovieDto> movies = MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(1));
			if (!movies.isEmpty()) {
				return movies;
			} else {
				throw new ServiceException(ERROR_MOVIES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_RENT_MOVIES, e);
		}
	}

	public List<MovieDto> returnMovie(Long id, UserForm userForm) {
		try {
			Optional<Rent> listRent = rentRepository.findById(id);
//			User user = userRepository.findByEmail(userForm.getEmail());
//			user.getRented().contains(listRent.get());
			if (listRent.isPresent()) {
				if (listRent.get().getUser().getEmail().equals(userForm.getEmail())) {
					Movie movie = listRent.get().getMovie();
					if (movie.getCurrentQuantity() < movie.getTotalAmount()) {
						movie.setCurrentQuantity(movie.getCurrentQuantity() + 1);
						if (movie.getCurrentQuantity() == 1) {
							movie.setAvailable(true);
						}
						Rent rent = new Rent(listRent.get().getUser(), listRent.get().getMovie(), Status.RETURNED);
						rentRepository.saveAndFlush(rent);
					}					
				}
				return MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(1));
			} else {
				throw new ServiceException(ERROR_MOVIES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_RETURN_MOVIES, e);
		}
	}

}
