package com.moviestore.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.moviestore.model.Movie;

public class MovieDto {

	private String name;
	
	public MovieDto(Movie movie) {
		this.name = movie.getName();
	}
	
	public String getName() {
		return name;
	}
	
	public static List<MovieDto> convert(List<Movie> listMovies) {
		return listMovies.stream().map(MovieDto::new).collect(Collectors.toList());
	}

}
