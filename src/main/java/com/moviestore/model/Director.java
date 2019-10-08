package com.moviestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity 
@Table(name = "directors")
public class Director extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Required name field.")
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
    @ManyToOne
    @JoinColumn
    private Movie movie;
      
	public Director(@NotBlank(message = "Required name field.") String name, Movie movie) {
		super();
		this.name = name;
		this.movie = movie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
        
}
