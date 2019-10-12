package com.moviestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.error.ServiceException;
import com.moviestore.repository.MovieRepository;

@Service
public class MovieService {


	private static final String ERROR_LIST_MOVIES = "Error listing movies.";
	private static final String ERROR_SEARCH_MOVIES = "Error search movie.";
	private static final String ERROR_MOVIES_NOT_FOUND = "No movies found.";

	@Autowired
	private MovieRepository movieRepository;


	public List<MovieDto> listAllAvailableMovies() {
		try {
			List<MovieDto> listAllavailableMovies = MovieDto
					.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(0));
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

	

}
