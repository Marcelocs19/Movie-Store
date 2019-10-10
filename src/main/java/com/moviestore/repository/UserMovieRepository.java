package com.moviestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviestore.model.UserMovie;

public interface UserMovieRepository extends JpaRepository<UserMovie, Long>{
	
	List<UserMovie> findByReturned(boolean returned);
	
}
