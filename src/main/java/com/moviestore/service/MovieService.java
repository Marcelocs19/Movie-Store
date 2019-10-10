package com.moviestore.service;

import java.util.Collection;

import com.moviestore.model.Movie;
import com.moviestore.repository.MovieRepository;
import com.moviestore.repository.UserMovieRepository;

public class MovieService {
	
	private final MovieRepository movieRepository;
	private final UserMovieRepository userMovieRepository;
	
	public MovieService(MovieRepository movieRepository, UserMovieRepository userMovieRepository) {
		this.movieRepository = movieRepository;
		this.userMovieRepository = userMovieRepository;
	}


}
