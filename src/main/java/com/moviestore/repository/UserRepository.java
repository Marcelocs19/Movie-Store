package com.moviestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviestore.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

}