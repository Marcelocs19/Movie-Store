package com.moviestore.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.model.Movie;
import com.moviestore.service.MovieService;

//@RestController
//@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
//	
//	@GetMapping
//	public ResponseEntity<MovieDto> listAvailableMovies(){
//		Collection<Movie> list = this.movieService.listAvailableMovie();
//		List<MovieDto> movieDto = MovieDto.convert(list);			
//		return new ResponseEntity<MovieDto>(HttpStatus.OK);
//	}
	
}
