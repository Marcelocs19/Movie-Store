package com.moviestore.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rent")
public class Rent extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "user",nullable = false)
	private User user;
	
	@ManyToOne
	@NotNull(message = "At least one movie required.")
	private Movie movie;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	//private LocalDateTime date = LocalDateTime.now();
	
	public Rent() {
		
	}	

	public Rent(User user, @NotNull(message = "At least one movie required.") Movie movie, Status status) {
		super();
		this.user = user;
		this.movie = movie;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
