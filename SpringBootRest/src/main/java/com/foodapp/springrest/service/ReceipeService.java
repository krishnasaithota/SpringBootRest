package com.foodapp.springrest.service;

import java.util.List;


import com.foodapp.springrest.entity.IngredientsEntity;
import com.foodapp.springrest.entity.ReceipeEntity;
import com.foodapp.springrest.model.Ingredients;
import com.foodapp.springrest.model.Receipe;


public interface ReceipeService {

	public List<ReceipeEntity> getAllReciepies();

	public List<IngredientsEntity> getAllIngredients();

	public List<ReceipeEntity> saveReciepe(List<Receipe> receipe);

	public List<ReceipeEntity> getReciepeByIngredients(Ingredients ingredient);

	public boolean existsByTitle(String receipeTitle);
}
