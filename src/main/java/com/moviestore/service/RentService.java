package com.moviestore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.controller.form.UserForm;
import com.moviestore.error.ServiceException;
import com.moviestore.model.Movie;
import com.moviestore.model.Rent;
import com.moviestore.model.Status;
import com.moviestore.model.User;
import com.moviestore.repository.MovieRepository;
import com.moviestore.repository.RentRepository;
import com.moviestore.repository.UserRepository;
import com.moviestore.security.AuthenticationTokenFilter;
import com.moviestore.security.TokenService;

@Service
public class RentService {

	private static final String ERROR_RETURN_MOVIES = "Error return movie.";
	private static final String ERROR_RENT_MOVIES = "Error rent movie.";
	private static final String ERROR_RENT_NOT_FOUND = "No rent found";
	private static final String ERROR_USER = "Error User and UserToken are diferents.";
	private static final String ERROR_RENT_SAME_MOVIE = "Error you are trying to rent the same movie you already rented.";
	private static final String ERROR_RETURN_MOVIE = "Error all movies are already returned.";

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenService tokenService;

	public List<MovieDto> rentMovie(Long id, UserForm userForm, HttpServletRequest request) {
		try {
			AuthenticationTokenFilter aux = new AuthenticationTokenFilter(tokenService, userRepository);
			String token = aux.restoreToken(request);
			Long idUser = tokenService.getIdUser(token);
			Optional<Movie> movie = movieRepository.findById(id);
			if (movie.isPresent()) {
				if (movie.get().getCurrentQuantity() == 0) {
					movie.get().setAvailable(false);
				} else {
					Optional<User> userToken = userRepository.findById(idUser);
					Optional<User> user = userRepository.findByEmail(userForm.getEmail());
					if (userToken.get().getId() == user.get().getId()) {
						List<Rent> listRent = rentRepository.findByMovie_idAndStatusAndUser_Email(id, Status.RENTED,
								user.get().getEmail());
						if (listRent.isEmpty()) {
							movie.get().setCurrentQuantity(movie.get().getCurrentQuantity() - 1);
							Rent rent = new Rent(user.get(), movie.get(), Status.RENTED);
							rentRepository.saveAndFlush(rent);
						} else {
							throw new ServiceException(ERROR_RENT_SAME_MOVIE);
						}
					}else {
						throw new ServiceException(ERROR_USER);
					}
				}
				return MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(0));
			} else {
				throw new ServiceException(ERROR_RENT_MOVIES);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_RENT_NOT_FOUND, e);
		}
	}

	public List<MovieDto> returnMovie(Long id, UserForm userForm, HttpServletRequest request) {
		try {
			AuthenticationTokenFilter aux = new AuthenticationTokenFilter(tokenService, userRepository);
			String token = aux.restoreToken(request);
			Long idUser = tokenService.getIdUser(token);

			Optional<Rent> rent = rentRepository.findById(id);
			Optional<Movie> movie = movieRepository.findById(rent.get().getMovie().getId());
			Optional<User> userToken = userRepository.findById(idUser);
			Optional<User> user = userRepository.findByEmail(userForm.getEmail());
			
			if (rent.isPresent() && movie.isPresent()) {
				if (userToken.get().getId() == user.get().getId()) {
					if (movie.get().getCurrentQuantity() < movie.get().getTotalAmount()) {
						rent.get().setDate_return(LocalDateTime.now());
						rent.get().setStatus(Status.RETURNED);
						movie.get().setAvailable(true);
						movie.get().setCurrentQuantity(movie.get().getCurrentQuantity() + 1);
					}else {
						throw new ServiceException(ERROR_RETURN_MOVIE);
					}
				} else {
					throw new ServiceException(ERROR_USER);
				}
				return MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(0));
			} else {
				throw new ServiceException(ERROR_RETURN_MOVIES);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_RENT_NOT_FOUND, e);
		}
	}

}
