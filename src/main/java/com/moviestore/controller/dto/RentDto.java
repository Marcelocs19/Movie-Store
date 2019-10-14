package com.moviestore.controller.dto;

import java.time.LocalDateTime;

import com.moviestore.model.Movie;
import com.moviestore.model.Rent;

public class RentDto {
	
	private Long id;
	
	private Movie movie;
	
	private LocalDateTime date_rent;
	
	private LocalDateTime date_return;

	public RentDto(Rent rent) {
		this.id = rent.getId();
		this.movie = rent.getMovie();
		this.date_rent = rent.getDate_rent();
		this.date_return = rent.getDate_return();
	}
	
	public Long getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public LocalDateTime getDate_rent() {
		return date_rent;
	}

	public LocalDateTime getDate_return() {
		return date_return;
	}
	
	
	
}
