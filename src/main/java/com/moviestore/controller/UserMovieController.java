package com.moviestore.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.model.UserMovie;
import com.moviestore.service.UserMovieService;

@RestController
@RequestMapping("/rent")
public class UserMovieController {

	private final UserMovieService userMovieService;

	public UserMovieController(UserMovieService userMovieService) {
		super();
		this.userMovieService = userMovieService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<Collection<UserMovie>> listAvailableMovies() throws Exception {
		try {
			Collection<UserMovie> listAvailableMovies = userMovieService.listAvailableMovie();
			if(listAvailableMovies.isEmpty()) {
				return new ResponseEntity<Collection<UserMovie>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<UserMovie>>(listAvailableMovies, HttpStatus.OK);			
			
		} catch (Exception e) {
			throw new Exception("Error");
		}

	}

}
