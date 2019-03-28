package com.foodapp.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.springrest.entity.ReceipeEntity;

@Repository
public interface ReceipeRepository extends JpaRepository<ReceipeEntity, Integer> {

	boolean existsByTitle(String receipeTitle);
}
