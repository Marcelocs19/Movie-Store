package com.moviestore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.model.Movie;
import com.moviestore.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Movie>> searchMovies(String name) throws Exception {
		try {
			List<Movie> listAvailableMovies = movieService.searchMovie(name);
			if(listAvailableMovies.isEmpty()) {
				return new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
			}			
			return new ResponseEntity<List<Movie>>(listAvailableMovies, HttpStatus.OK);			
			
		} catch (Exception e) {
			throw new Exception("Error");
		}

	}

	
}
