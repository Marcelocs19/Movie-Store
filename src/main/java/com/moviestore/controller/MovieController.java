package com.moviestore.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.form.UserForm;
import com.moviestore.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private static final String ERROR_LIST_AVAILABLE_MOVIES = "Error listing movies.";
	private static final String ERROR_SEARCH_MOVIES = "Error searching movie.";
	private static final String ERROR_RENT_MOVIES = "Error when renting the movie.";
	private static final String ERROR_RETURN_MOVIES = "Error returning movie.";
	
	private static final String SEARCH = "/search";
	private static final String RENTMOVIE = "/{id}/rent";
	private static final String RETURNMOVIE = "/{id}/return";	
	
	@Autowired
	private MovieService movieService;		
	
	@GetMapping
	public ResponseEntity<List<MovieDto>> listAvailableMovies() throws Exception {
		try {
			List<MovieDto> listAvailableMovies = movieService.listAllAvailableMovies();
			if(listAvailableMovies.isEmpty()) {
				return ResponseEntity.notFound().build();
			}			
			return ResponseEntity.ok().body(listAvailableMovies);
		} catch (Exception e) {
			throw new Exception(ERROR_LIST_AVAILABLE_MOVIES);
		}

	}
	
	
	@GetMapping(SEARCH)
	public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam("title") String title) throws Exception {
		try {
			List<MovieDto> listSearchMovies = movieService.searchMovie(title);
			if(listSearchMovies.isEmpty()) {
				return ResponseEntity.notFound().build();
			}			
			return ResponseEntity.ok().body(listSearchMovies);			
		} catch (Exception e) {
			throw new Exception(ERROR_SEARCH_MOVIES);
		}

	}
	
	@PutMapping(RENTMOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> rentMovie(@PathVariable Long id, @RequestBody UserForm userForm) throws Exception {
		try {
			List<MovieDto> rentMovie = movieService.rentMovie(id,userForm);
			if (rentMovie.isEmpty()) {
				return ResponseEntity.notFound().build();
			}					
			return ResponseEntity.ok(rentMovie);
		} catch (Exception e) {
			throw new Exception(ERROR_RENT_MOVIES);
		}

	}

	@PutMapping(RETURNMOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> returnMovie(@PathVariable Long id, @RequestBody UserForm userForm) throws Exception {
		try {
			List<MovieDto> returnMovie = movieService.returnMovie(id,userForm);
			if (returnMovie.isEmpty()) {
				return ResponseEntity.notFound().build();				
			}			
			return ResponseEntity.ok(returnMovie);
		} catch (Exception e) {
			throw new Exception(ERROR_RETURN_MOVIES);
		}

	}
	
	
	
}
