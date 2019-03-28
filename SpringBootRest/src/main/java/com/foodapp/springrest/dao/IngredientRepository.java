package com.foodapp.springrest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.springrest.entity.IngredientsEntity;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientsEntity, Integer>{

	List<IngredientsEntity> findByIngredient(String ingredient);
}
