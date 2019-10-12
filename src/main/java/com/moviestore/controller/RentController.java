package com.moviestore.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.form.UserForm;
import com.moviestore.service.RentService;

@RestController
@RequestMapping("/rent")
public class RentController {

	private static final String ERROR_RENT_MOVIES = "Error when renting the movie.";
	private static final String ERROR_RETURN_MOVIES = "Error returning movie.";
	
	private static final String RENTMOVIE = "/rent/{id}";
	private static final String RETURNMOVIE = "/return/{id}";
	
	@Autowired
	private RentService rentService;
	
	@PutMapping(RENTMOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> rentMovie(@PathVariable Long id, @RequestBody UserForm userForm) throws Exception {
		try {
			List<MovieDto> rentMovie = rentService.rentMovie(id,userForm);
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
	public ResponseEntity<List<MovieDto>> returnMovie(@PathVariable(name = "id") Long id_rent, @RequestBody UserForm userForm) throws Exception {
		try {
			List<MovieDto> returnMovie = rentService.returnMovie(id_rent,userForm); 
			if (returnMovie.isEmpty()) {
				return ResponseEntity.notFound().build();				
			}			
			return ResponseEntity.ok(returnMovie);
		} catch (Exception e) {
			throw new Exception(ERROR_RETURN_MOVIES);
		}

	}
	
}
