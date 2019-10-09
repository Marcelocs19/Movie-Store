package com.moviestore.service;

import java.util.List;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.model.Movie;
import com.moviestore.repository.MovieRepository;

public class MovieService {
	
	private final MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
//	public List<MovieDto> listAvailableMovie(){
//		try {
//			List<Movie> listMovies = movieRepository.findAll();
//		} catch (Exception e) {
//		}
//	}

}
