package com.moviestore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moviestore.error.ServiceException;
import com.moviestore.model.UserMovie;
import com.moviestore.repository.UserMovieRepository;

@Service
public class UserMovieService {

	private static final String ERROR_LIST_MOVIES = "Error listing movies.";
	private static final String LIST_MOVIES_NOT_FOUND = "No movies found.";
	
	private final UserMovieRepository userMovieRepository;

	public UserMovieService(UserMovieRepository userMovieRepository) {
		super();
		this.userMovieRepository = userMovieRepository;
	}

	public Collection<UserMovie> listAvailableMovie() {
		try {
			Collection<UserMovie> available = userMovieRepository.findByReturned(true);
			if (!available.isEmpty()) {
				return available;
			} else {
				throw new ServiceException(LIST_MOVIES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_LIST_MOVIES,e);
		}
	}

}
