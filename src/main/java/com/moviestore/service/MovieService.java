package com.moviestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public List<MovieDto> listAllAvailableMovies() {
		return MovieDto.convertMoviesToDto(movieRepository.findByCurrentQuantityGreaterThan(0));
	}

	public List<MovieDto> searchMovie(String title) {
		String titleTrim = title.trim();
		return MovieDto.convertMoviesToDto(movieRepository.findByTitle(titleTrim));
	}

}
