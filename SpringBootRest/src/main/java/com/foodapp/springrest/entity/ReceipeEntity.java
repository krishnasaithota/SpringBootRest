package com.foodapp.springrest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity

@Table(name = "receipe")


public class ReceipeEntity {

	@Id
	@NotNull(message = "id is required")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull(message = "title is required")
	
	@JsonProperty("title")
	private String title;

	@NotNull(message = "href is required")
	
	private String href;

	@NotNull(message = "thumbnail is required")
	
	private String thumbnail;

	@NotNull(message = "ingredients is required")
	@Convert(converter = ListToStringConverter.class)
	private List<String> ingredients;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "receipe_ingredients", joinColumns = { @JoinColumn(name = "Receipe_id") }, inverseJoinColumns = {
			@JoinColumn(name = "ingredient_id") })
	@JsonIgnore
	
	private final List<IngredientsEntity> ingredientsCollection = new ArrayList<>();

	
	@JsonProperty("status")
	private boolean status = false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public boolean isStatus() {
		return status; 
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<IngredientsEntity> getIngredientsCollection() {
		return ingredientsCollection;
	}

	public ReceipeEntity(@NotNull(message = "id is required") long id, @NotNull(message = "title is required") String title,
			@NotNull(message = "href is required") String href,
			@NotNull(message = "thumbnail is required") String thumbnail,
			@NotNull(message = "ingredients is required") List<String> ingredients, boolean status) {
	
		this.id = id;
		this.title = title;
		this.href = href;
		this.thumbnail = thumbnail;
		this.ingredients = ingredients;
		this.status = status;
	}

	public ReceipeEntity() {
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((ingredientsCollection == null) ? 0 : ingredientsCollection.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((thumbnail == null) ? 0 : thumbnail.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		ReceipeEntity other = (ReceipeEntity) obj;
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
			return false;
		if (id != other.id)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (ingredientsCollection == null) {
			if (other.ingredientsCollection != null)
				return false;
		} else if (!ingredientsCollection.equals(other.ingredientsCollection))
			return false;
		if (status != other.status)
			return false;
		if (thumbnail == null) {
			if (other.thumbnail != null)
				return false;
		} else if (!thumbnail.equals(other.thumbnail))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	
}
