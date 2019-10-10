package com.moviestore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity 
@Table(name = "movies")
public class Movie extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Required name field.")
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Min(0)
	@Column(name = "total_amount", nullable = false)
	private int totalAmount;
	
	@Min(0)
	@Column(name = "current_quantity", nullable = false)
	private int currentQuantity;
   
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private Set<Director> diretor;
			
	public Movie() {
		super();
	}

	public Movie(@NotBlank(message = "Required name field.") String name, @Min(0) int totalAmount,
			@Min(0) int currentQuantity, Set<Director> diretor) {
		super();
		this.name = name;
		this.totalAmount = totalAmount;
		this.currentQuantity = currentQuantity;
		this.diretor = diretor;
	}

		
	public Set<Director> getDiretor() {
		return diretor;
	}

	public void setDiretor(Set<Director> diretor) {
		this.diretor = diretor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Movie [name=" + name + ", totalAmount=" + totalAmount + ", currentQuantity=" + currentQuantity
				+ ", diretor=" + diretor + "]";
	}	

	
	
}
