package com.moviestore.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.moviestore.model.pk.UserMoviePK;

@Entity
@Table(name = "user_movie")
public class UserMovie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private UserMoviePK user_movie_id;

	private boolean returned;

	public UserMovie() {
		super();
	}

	public UserMovie(User user, Movie movie, boolean returned) {
		super();
		user_movie_id.setUser(user);
		user_movie_id.setMovie(movie);
		this.returned = returned;
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
			if (other.user_movie_id != null) {
				return false;
			}
		} else if (!user_movie_id.equals(other.user_movie_id)) {
			return false;
		}
		return true;
	}

}
