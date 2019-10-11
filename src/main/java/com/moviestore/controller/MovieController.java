package com.moviestore.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.form.UserForm;
import com.moviestore.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final String SEARCH = "/search";
	private static final String RENTMOVIE = "/{id}/rent";
	private static final String RETURNMOVIE = "/{id}/return";
	
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
	
	
	@GetMapping(SEARCH)
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
	
	@PutMapping(RENTMOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> rentMovie(@PathVariable Long id, @RequestBody UserForm userForm) throws Exception {
		try {
			List<MovieDto> rentMovie = movieService.rentMovie(id,userForm);
			if (!rentMovie.isEmpty()) {
				return ResponseEntity.ok(rentMovie);
			}			
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			throw new Exception("Error");
		}

	}

	@PutMapping(RETURNMOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> returnMovie(@PathVariable Long id, @RequestBody UserForm userForm) throws Exception {
		try {
			List<MovieDto> returnMovie = movieService.returnMovie(id,userForm);
			if (!returnMovie.isEmpty()) {
				return ResponseEntity.ok(returnMovie);
			}			
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			throw new Exception("Error");
		}

	}
	
	
	
}
