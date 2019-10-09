package com.moviestore.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviestore.model.Movie;
import com.moviestore.model.UserMovie;

public interface UserMovieRepository extends JpaRepository<UserMovie, Long>{
	
	Collection<Movie> findByReturned(boolean returned);
	
}
