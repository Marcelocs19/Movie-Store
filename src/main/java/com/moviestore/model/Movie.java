package com.moviestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity 
@Table(name = "movies")
public class Movie extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Required title field.")
	@Column(name = "title", length = 100, nullable = false)
	private String title;
	
	@Min(0)
	@Column(name = "total_amount", nullable = false)
	private int totalAmount;
	
	@Min(0)
	@Column(name = "current_quantity", nullable = false)
	private int currentQuantity;
   
	@NotBlank(message = "Required name of director field.")
	@Column(name = "director_name", length = 100, nullable = false)
	private String director_name;
	
				
	public Movie() {
		super();
	}

	public Movie(@NotBlank(message = "Required title field.") String title, @Min(0) int totalAmount,
			@Min(0) int currentQuantity, @NotBlank(message = "Required title field.") String director_name) {
		super();
		this.title = title;
		this.totalAmount = totalAmount;
		this.currentQuantity = currentQuantity;
		this.director_name = director_name;
	}

	public String getDirector_name() {
		return director_name;
	}

	public void setdirector_name(String director_name) {
		this.director_name = director_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", totalAmount=" + totalAmount + ", currentQuantity=" + currentQuantity
				+ ", director_name=" + director_name + "]";
	}	
	
}
