package com.foodapp.springrest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity

@Table(name = "ingredients")

public class IngredientsEntity {

	@Id
	@NotNull(message = "id is required")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "ingredient is required")

	private String ingredient;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredientsCollection")

	private final List<ReceipeEntity> receipesCollection = new ArrayList<ReceipeEntity>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public List<ReceipeEntity> getReceipesCollection() {
		return receipesCollection;
	}

	public IngredientsEntity(@NotNull(message = "id is required") long id,
			@NotNull(message = "ingredient is required") String ingredient) {
		
		this.id = id;
		this.ingredient = ingredient;
	}

	public IngredientsEntity() {
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + ((receipesCollection == null) ? 0 : receipesCollection.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredientsEntity other = (IngredientsEntity) obj;
		if (id != other.id)
			return false;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (receipesCollection == null) {
			if (other.receipesCollection != null)
				return false;
		} else if (!receipesCollection.equals(other.receipesCollection))
			return false;
		return true;
	}
	
	
	

}

