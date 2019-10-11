package com.moviestore.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.moviestore.model.Movie;
import com.moviestore.model.Rent;

public class MovieDto {

	private Long id;
	private String title;
	private int currentQuantity;
	private String director;
	

	public MovieDto(Movie movie) {
		this.id = movie.getId();
		this.title = movie.getTitle();
		this.currentQuantity = movie.getCurrentQuantity();
		this.director = movie.getDirector_name();
	}

	public MovieDto(Rent rent) {
		this.id = rent.getMovies().getId();
		this.title = rent.getMovies().getTitle();
	}
			
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
		
	public int getCurrentQuantity() {
		return currentQuantity;
	}
	
	
	public String getDirector() {
		return director;
	}

	public static List<MovieDto> convertMoviesToDto(List<Movie> listMovies) {
		return listMovies.stream().map(MovieDto::new).collect(Collectors.toList());
	}


}
