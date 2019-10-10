package com.moviestore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.MovieDto;
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
	public ResponseEntity<List<MovieDto>> listAvailableMovies() throws Exception {
		try {
			List<MovieDto> listAvailableMovies = movieService.listAllAvailableMovies();
			if(listAvailableMovies.isEmpty()) {
				return new ResponseEntity<List<MovieDto>>(HttpStatus.NOT_FOUND);
			}			
			return new ResponseEntity<List<MovieDto>>(listAvailableMovies, HttpStatus.OK);			
			
		} catch (Exception e) {
			throw new Exception("Error");
		}

	}
	
	
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam("title") String title) throws Exception {
		try {
			List<MovieDto> listSearchMovies = movieService.searchMovie(title);
			if(listSearchMovies.isEmpty()) {
				return new ResponseEntity<List<MovieDto>>(HttpStatus.NOT_FOUND);
			}			
			return new ResponseEntity<List<MovieDto>>(listSearchMovies, HttpStatus.OK);			
			
		} catch (Exception e) {
			throw new Exception("Error");
		}

	}

	
	
	
}
