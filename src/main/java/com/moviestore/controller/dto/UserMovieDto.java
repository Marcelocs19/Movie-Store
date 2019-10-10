package com.moviestore.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.moviestore.model.UserMovie;

public class UserMovieDto {
	
	private String name;
	private int currentQuantity;
	
	public UserMovieDto(UserMovie userMovie) {
		this.name = userMovie.getMovie().getTitle();
		this.currentQuantity = userMovie.getMovie().getCurrentQuantity();
	}

	public String getName() {
		return name;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}	

	public static List<UserMovieDto> convert(List<UserMovie> listUserMovies) {
		return listUserMovies.stream().map(UserMovieDto::new).collect(Collectors.toList());
	}
	
}
