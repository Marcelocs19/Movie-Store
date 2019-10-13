package com.moviestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviestore.model.Rent;
import com.moviestore.model.Status;

public interface RentRepository  extends JpaRepository<Rent, Long>{

	List<Rent> findByMovie_id(Long id);

	List<Rent> findByMovie_idAndStatusAndUser_Email(Long id,Status status ,String email);
	
	
}
