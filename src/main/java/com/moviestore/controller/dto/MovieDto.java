package com.moviestore.controller.dto;

import java.util.ArrayList;
import java.util.Collection;
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

	public static List<MovieDto> convert(Collection<Movie> listMovies) {
		List<MovieDto> listMoviesDto = new ArrayList<>();
		while (listMovies.iterator().hasNext()) {
			MovieDto dto = new MovieDto(listMovies.iterator().next());
			listMoviesDto.add(dto);
		}
		return listMoviesDto;
	}
}
