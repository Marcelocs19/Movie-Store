package com.moviestore.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moviestore.model.pk.UserMoviePK;

@Entity
@Table(name = "user_movie")
public class UserMovie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@EmbeddedId
	private UserMoviePK user_movie_id;
	
	private boolean returned;
	
    @Column(name = "date_rented", nullable = false) 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date_rented = LocalDateTime.now();
	
    @Column(name = "date_returned") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date_returned;

	public UserMovie() {
		super();
	}

	public UserMovie(User user, Movie movie, boolean returned, LocalDateTime date_rented, LocalDateTime date_returned) {
		super();
		user_movie_id.setUser(user);
		user_movie_id.setMovie(movie);
		this.returned = returned;
		this.date_rented = date_rented;
		this.date_returned = date_returned;
	}
	
	public User getUser() {
		return user_movie_id.getUser();
	}
	
	public void setUser(User user) {
		user_movie_id.setUser(user);
	}
	
	public Movie getMovie() {
		return user_movie_id.getMovie();
	}
	
	public void setMovie(Movie movie) {
		user_movie_id.setMovie(movie);
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
		result = prime * result + ((user_movie_id == null) ? 0 : user_movie_id.hashCode());
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
		UserMovie other = (UserMovie) obj;
		if (user_movie_id == null) {
			if (other.user_movie_id != null)
				return false;
		} else if (!user_movie_id.equals(other.user_movie_id))
			return false;
		return true;
	}
     
		
}
