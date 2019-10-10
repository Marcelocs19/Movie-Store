package com.moviestore.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.moviestore.model.Movie;
import com.moviestore.model.Rent;

public class MovieDto {

	private Long id;
	private String name;

	public MovieDto(Movie movie) {
		this.id = movie.getId();
		this.name = movie.getTitle();
	}

	public MovieDto(Rent rent) {
		this.id = rent.getMovies().getId();
		this.name = rent.getMovies().getTitle();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static List<MovieDto> convertMoviesToDto(List<Movie> listMovies) {
		return listMovies.stream().map(MovieDto::new).collect(Collectors.toList());
	}


}
