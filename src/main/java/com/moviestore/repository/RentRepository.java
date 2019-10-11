package com.moviestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviestore.model.Rent;

public interface RentRepository  extends JpaRepository<Rent, Long>{

	List<Rent> findByMovies_id(Long id);

}
