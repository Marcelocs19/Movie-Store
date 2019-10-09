package com.moviestore.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.moviestore.model.Movie;

public class MovieDto {

	private String name;
	private int currentQuantity;
	
	public MovieDto(Movie movie) {
		this.name = movie.getName();
		this.currentQuantity = movie.getCurrentQuantity();
	}

	public String getName() {
		return name;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}
	
	public static List<MovieDto> convert(List<Movie> listMovies){
		List<MovieDto> listMoviesDto = new ArrayList<>();
		for(int i = 0; i < listMovies.size(); i++) {
			MovieDto dto = new MovieDto(listMovies.get(i));
			listMoviesDto.add(dto);
		}
		return listMoviesDto;
	}
}
