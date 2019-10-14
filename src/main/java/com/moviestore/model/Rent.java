package com.moviestore.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	
	//@NotNull(message = "At least one user required.")
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user")
	private User user;
	
	@ManyToOne
	@NotNull(message = "At least one movie required.")
	private Movie movie;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name = "date_rent") 
	private LocalDateTime date_rent = LocalDateTime.now();
	
	@Column(name = "date_return") 
	private LocalDateTime date_return;
	
	public Rent() {
		
	}	

	public Rent(@NotNull(message = "At least one user required.") User user,
			@NotNull(message = "At least one movie required.") Movie movie, Status status) {
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

	public LocalDateTime getDate_rent() {
		return date_rent;
	}

	public void setDate_rent(LocalDateTime date_rent) {
		this.date_rent = date_rent;
	}

	public LocalDateTime getDate_return() {
		return date_return;
	}

	public void setDate_return(LocalDateTime date_return) {
		this.date_return = date_return;
	}

	@Override
	public String toString() {
		return "Rent [user=" + user + ", movie=" + movie + ", status=" + status + ", date_rent=" + date_rent
				+ ", date_return=" + date_return + "]";
	}

		
}
