package com.moviestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviestore.model.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long>{

	
	
}
