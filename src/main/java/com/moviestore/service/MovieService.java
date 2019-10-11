package com.moviestore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.error.ServiceException;
import com.moviestore.form.UserForm;
import com.moviestore.model.Movie;
import com.moviestore.model.Rent;
import com.moviestore.model.User;
import com.moviestore.repository.MovieRepository;
import com.moviestore.repository.RentRepository;
import com.moviestore.repository.UserRepository;

@Service
public class MovieService {
	
	private static final String ERROR_RENT_MOVIES = "Error rent movies.";
	private static final String ERROR_LIST_MOVIES = "Error listing movies.";
	private static final String ERROR_SEARCH_MOVIES = "Error search movies.";
	private static final String ERROR_MOVIES_NOT_FOUND = "No movies found.";
	
	
	
	private final MovieRepository movieRepository;
	private final RentRepository rentRepository;
	private final UserRepository userRepository;

	public MovieService(MovieRepository movieRepository,RentRepository rentRepository,UserRepository userRepository) {
		super();
		this.movieRepository = movieRepository;
		this.rentRepository = rentRepository;
		this.userRepository = userRepository;
	}

	public List<MovieDto> listAllAvailableMovies(){
		try {
			List<MovieDto> listAllavailableMovies = MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(1));
			if(!listAllavailableMovies.isEmpty()) {
				return listAllavailableMovies;
			} else {
				throw new ServiceException(ERROR_MOVIES_NOT_FOUND);
			}
			
		} catch (Exception e) {
			throw new ServiceException(ERROR_LIST_MOVIES,e);
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
			throw new ServiceException(ERROR_SEARCH_MOVIES,e);
		}
	}
	
	public List<MovieDto> rentMovie(Long id, UserForm userForm) {
		try {
			Movie movie = movieRepository.getOne(id);
			if(movie.getCurrentQuantity() == 0) {
				movie.setAvailable(false);
			}else {
				movie.setCurrentQuantity(movie.getCurrentQuantity() - 1);
				User user = userRepository.findByEmail(userForm.getEmail());
				Rent rent = new Rent(user,movie);
				rentRepository.saveAndFlush(rent);				
			}
			List<MovieDto> movies = MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(1));
			if (!movies.isEmpty()) {
				return movies;
			} else {
				throw new ServiceException(ERROR_MOVIES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_RENT_MOVIES,e);
		}
	}

	public List<MovieDto> returnMovie(Long id, UserForm userForm) {
		try {
			Movie movie = movieRepository.getOne(id);
			if(movie.getCurrentQuantity() < movie.getTotalAmount()) {
				movie.setCurrentQuantity(movie.getCurrentQuantity() + 1);
				if(movie.getCurrentQuantity() == 1) {
					movie.setAvailable(true);
				}
				User user = userRepository.findByEmail(userForm.getEmail());
				Rent rent = new Rent(user,movie);
				rentRepository.saveAndFlush(rent);
			}
			List<MovieDto> movies = MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(1));
			if (!movies.isEmpty()) {
				return movies;
			} else {
				throw new ServiceException(ERROR_MOVIES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_RENT_MOVIES,e);
		}
	}

}
