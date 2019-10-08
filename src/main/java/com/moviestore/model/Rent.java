package com.moviestore.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moviestore.model.pk.UserMoviePK;

@Entity
@Table(name = "rent")
public class Rent implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserMoviePK id;
	
	private boolean returned;
	
    @Column(name = "date_read") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date_rented = LocalDateTime.now();
	
    @Column(name = "date_returned") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date_returned;

	public Rent() {
		super();
	}

	public Rent(User user, Movie movie, boolean returned, LocalDateTime date_rented, LocalDateTime date_returned) {
		super();
		id.setUser(user);
		id.setMovie(movie);
		this.returned = returned;
		this.date_rented = date_rented;
		this.date_returned = date_returned;
	}
	
	public User getUser() {
		return id.getUser();
	}
	
	public void setUser(User user) {
		id.setUser(user);
	}
	
	public Movie getMovie() {
		return id.getMovie();
	}
	
	public void setMovie(Movie movie) {
		id.setMovie(movie);
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public LocalDateTime getDate_rented() {
		return date_rented;
	}

	public void setDate_rented(LocalDateTime date_rented) {
		this.date_rented = date_rented;
	}

	public LocalDateTime getDate_returned() {
		return date_returned;
	}

	public void setDate_returned(LocalDateTime date_returned) {
		this.date_returned = date_returned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rent other = (Rent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
     
		
}
