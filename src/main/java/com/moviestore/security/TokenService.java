package com.moviestore.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.moviestore.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${movie-store.jwt.expiration}")
	private String expiration;	
	
	@Value("${movie-store.jwt.secret}")
	private String secret;

	public String createToken(Authentication authentication) {
		User logged = (User)authentication.getPrincipal();
		Date date = new Date();
		Date dateExpiration = new Date(date.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("API Movie Store")
				.setSubject(logged.getId().toString())
				.setIssuedAt(date)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
}
