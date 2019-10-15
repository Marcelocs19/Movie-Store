package com.moviestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private static final String SEARCH = "/search";

	@Autowired
	private MovieService movieService;

	@GetMapping
	public ResponseEntity<List<MovieDto>> listAvailableMovies() {
		List<MovieDto> listAvailableMovies = movieService.listAllAvailableMovies();
		if (listAvailableMovies.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listAvailableMovies);

	}

	@PostMapping(SEARCH)
	public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam("title") String title) {
		List<MovieDto> listSearchMovies = movieService.searchMovie(title);
		if (listSearchMovies.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(listSearchMovies);
	}

}
