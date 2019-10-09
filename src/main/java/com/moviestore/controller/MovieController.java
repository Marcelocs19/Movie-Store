package com.moviestore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
//	@GetMapping
//	public ResponseEntity<MovieDto> listAvailableMovies(){
//		try {
//			
//		} catch (Exception e) {
//		}
//		Collection<Owner> owners = this.clinicService.findAllOwners();
//		if (owners.isEmpty()) {
//			return new ResponseEntity<Collection<Owner>>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Collection<Owner>>(owners, HttpStatus.OK);
//	}
	
}
