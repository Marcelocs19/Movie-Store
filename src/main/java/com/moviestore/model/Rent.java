package com.moviestore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rent")
public class Rent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user",nullable = false)
	private User user;
	
	@ManyToOne
	@NotNull(message = "At least one movie required.")
	private Movie movies;


	
	public Rent(User user, @NotNull(message = "At least one movie required.") Movie movies) {
		super();
		this.user = user;
		this.movies = movies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovies() {
		return movies;
	}

	public void setMovies(Movie movie) {
		this.movies = movie;
	}


}
