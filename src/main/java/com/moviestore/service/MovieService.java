package com.moviestore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviestore.error.ServiceException;
import com.moviestore.model.Movie;
import com.moviestore.repository.MovieRepository;

@Service
public class MovieService {
	
	private static final String ERROR_SEARCH_MOVIES = "Error search movies.";
	private static final String SEARCH_MOVIES_NOT_FOUND = "No movies found.";
	
	private final MovieRepository movieRepository;

	public MovieService(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	public List<Movie> searchMovie(String name) {
		try {
			List<Movie> search = movieRepository.findByName(name);
			if (!search.isEmpty()) {
				return search;
			} else {
				throw new ServiceException(SEARCH_MOVIES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_SEARCH_MOVIES,e);
		}
	} 

}
