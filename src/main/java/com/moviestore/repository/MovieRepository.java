package com.moviestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviestore.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	List<Movie> findByTitle(String title);
	
	List<Movie> findByCurrentQuantityGreaterThan(int quantity);
	
}
