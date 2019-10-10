package com.moviestore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.error.ServiceException;
import com.moviestore.repository.MovieRepository;
import com.moviestore.repository.UserMovieRepository;

@Service
public class MovieService {
	
	private static final String ERROR_LIST_MOVIES = "Error listing movies.";
	private static final String ERROR_SEARCH_MOVIES = "Error search movies.";
	private static final String SEARCH_MOVIES_NOT_FOUND = "No movies found.";
	private static final String LIST_MOVIES_NOT_FOUND = "No movies found.";
	
	
	private final MovieRepository movieRepository;
	private final UserMovieRepository userMovieRepository;

	public MovieService(MovieRepository movieRepository, UserMovieRepository userMovieRepository) {
		super();
		this.movieRepository = movieRepository;
		this.userMovieRepository = userMovieRepository;
	}

	public List<MovieDto> listAllAvailableMovies(){
		try {
			List<MovieDto> listAllavailableMovies = MovieDto.convertUserMoviesDto(userMovieRepository.findByReturned(false));
			if(!listAllavailableMovies.isEmpty()) {
				return listAllavailableMovies;
			} else {
				throw new ServiceException(LIST_MOVIES_NOT_FOUND);
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
				throw new ServiceException(SEARCH_MOVIES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ServiceException(ERROR_SEARCH_MOVIES,e);
		}
	} 

}
