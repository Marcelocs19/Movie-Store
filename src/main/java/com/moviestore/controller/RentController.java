package com.moviestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	private static final String RENTMOVIE = "/{id}";
	private static final String RETURNMOVIE = "/return/{id}";

	@Autowired
	private RentService rentService;

	@PutMapping(RENTMOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> rentMovie(@PathVariable Long id, @RequestBody @Valid UserForm userForm,
			HttpServletRequest request) {
		List<MovieDto> rentMovie = rentService.rentMovie(id, userForm, request);
		if (rentMovie.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentMovie);

	}

	@PutMapping(RETURNMOVIE)
	@Transactional
	public ResponseEntity<List<MovieDto>> returnMovie(@PathVariable(name = "id") Long id_rent,
			@RequestBody @Valid UserForm userForm, HttpServletRequest request) {
		List<MovieDto> returnMovie = rentService.returnMovie(id_rent, userForm, request);
		if (returnMovie.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(returnMovie);

	}

}
