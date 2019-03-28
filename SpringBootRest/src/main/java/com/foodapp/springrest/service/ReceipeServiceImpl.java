package com.foodapp.springrest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodapp.springrest.dao.IngredientRepository;
import com.foodapp.springrest.dao.ReceipeRepository;
import com.foodapp.springrest.entity.IngredientsEntity;
import com.foodapp.springrest.entity.ReceipeEntity;
import com.foodapp.springrest.model.Ingredients;
import com.foodapp.springrest.model.Receipe;


@Service
public class ReceipeServiceImpl implements ReceipeService {
	private final static Logger LOGGER = LogManager.getLogger(ReceipeServiceImpl.class);
	@Autowired
	private ReceipeRepository receipeRepository;
	@Autowired
	private IngredientRepository ingredientRepository;

	@Transactional(readOnly = true)
	public List<ReceipeEntity> getAllReciepies() {
		LOGGER.info("fetching all reciepies");
		List<ReceipeEntity> receipes = new ArrayList<>();
		receipeRepository.findAll().forEach(r -> receipes.add(r));
		return receipes;
	}

	@Transactional(readOnly = true)
	public List<IngredientsEntity> getAllIngredients() {
		LOGGER.info("fetching all Ingredients");
		List<IngredientsEntity> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(i -> ingredients.add(i));
		return ingredients;
	}

	@Transactional
	public List<ReceipeEntity> saveReciepe(List<Receipe> receipes) {
		List<ReceipeEntity> receipeMOList = new ArrayList<>();
		ReceipeEntity receipe2;

		for (Receipe receipe : receipes) {
			receipe2 = new ReceipeEntity();
			receipe2.setHref(receipe.getHref());
			receipe2.setThumbnail(receipe.getThumbnail());
			receipe2.setTitle(receipe.getTitle());
			receipe2.setIngredients(Arrays.asList(receipe.getIngredients()));
			IngredientsEntity ingredientsEntity = new IngredientsEntity();
			for (String ingredientName : receipe2.getIngredients()) {
				ingredientsEntity = new IngredientsEntity();
				ingredientsEntity.setIngredient(ingredientName);

				receipe2.getIngredientsCollection().add(ingredientsEntity);
			}

			if (!existsByTitle(receipe.getTitle())) {
				receipe2.setStatus(true);
				receipeMOList.add(receipe2);
				receipeRepository.save(receipe2);
			} else
				receipeMOList.add(receipe2);

		}

		LOGGER.info("save reciepie data successfully");
		return receipeMOList;
	}

	public boolean existsByTitle(String receipeTitle) {
		LOGGER.info("Find By Title ::: {}", receipeTitle);

		return receipeRepository.existsByTitle(receipeTitle);

	}

	@Transactional(readOnly = true)
	public List<ReceipeEntity> getReciepeByIngredients(Ingredients ingredient) {
		List<ReceipeEntity> receipeMOList = new ArrayList<ReceipeEntity>();

		for (String ingredientName : ingredient.getIngredients()) {
			LOGGER.debug("Ingredient Name {}", ingredientName);

			List<IngredientsEntity> ingredientsMO = ingredientRepository.findByIngredient(ingredientName);
			for (IngredientsEntity ingredient1 : ingredientsMO) {
				receipeMOList.add(ingredient1.getReceipesCollection().get(0));
			}
		}
		Set<ReceipeEntity> change=new HashSet<>(receipeMOList);
		List<ReceipeEntity> receipeMOList1 = new ArrayList<ReceipeEntity>();
		receipeMOList1.addAll(change);

		LOGGER.debug("Retrieve Receipes by Ingredients return object:: {}", receipeMOList);

		return receipeMOList1;
	}

}

