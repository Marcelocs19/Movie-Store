package com.moviestore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviestore.controller.dto.UserMovieDto;
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
	public ResponseEntity<List<UserMovieDto>> listAvailableMovies() throws Exception {
		try {
			List<UserMovieDto> listAvailableMovies = UserMovieDto.convert(userMovieService.listAvailableMovie());
			if(listAvailableMovies.isEmpty()) {
				return new ResponseEntity<List<UserMovieDto>>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<List<UserMovieDto>>(listAvailableMovies, HttpStatus.OK);			
			
		} catch (Exception e) {
			throw new Exception("Error");
		}

	}

}
