package com.moviestore.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.MovieDto;
import com.moviestore.controller.form.UserForm;
import com.moviestore.service.RentService;

@RestController
@RequestMapping("/rent")
public class RentController {

	private static final String RENT_MOVIE = "/{id}";
	private static final String RETURN_MOVIE = "/return/{id}";

	@Autowired
	private RentService rentService;
	
	@PutMapping(RENT_MOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> rentMovie(@PathVariable(name = "id") Long idMovie,
			@RequestBody @Valid UserForm userForm, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			List<MovieDto> rentMovie = rentService.rentMovie(idMovie, userForm);
			if (!rentMovie.isEmpty()) {				
				return ResponseEntity.ok(rentMovie);
			}else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(RETURN_MOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> returnMovie(@PathVariable(name = "id") Long idRent,
			@RequestBody @Valid UserForm userForm) {
		List<MovieDto> returnMovie = rentService.returnMovie(idRent, userForm);
		if (returnMovie.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(returnMovie);

	}

}
