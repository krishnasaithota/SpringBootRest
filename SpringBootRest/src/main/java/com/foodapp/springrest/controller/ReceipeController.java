package com.foodapp.springrest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.springrest.entity.IngredientsEntity;
import com.foodapp.springrest.entity.ReceipeEntity;
import com.foodapp.springrest.exception.ResourceNotFoundException;
import com.foodapp.springrest.model.Ingredients;
import com.foodapp.springrest.model.Receipe;
import com.foodapp.springrest.service.ReceipeService;

@RestController
@RequestMapping("/api")
public class ReceipeController {
	private final static Logger LOGGER = LogManager.getLogger(ReceipeController.class);
	@Autowired
	private ReceipeService reciepeService;

	@GetMapping("/receipies")
	public ResponseEntity<List<ReceipeEntity>> getReceipies() throws ResourceNotFoundException {
		try {
			List<ReceipeEntity> receipes = reciepeService.getAllReciepies();
			LOGGER.info("fetching all receipies data");

			return new ResponseEntity<>(receipes, HttpStatus.OK);

		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("resource not found");
		}

	}

	@PostMapping("/receipies")
	public ResponseEntity<List<ReceipeEntity>> serachReciepeByIngredients(@RequestBody Ingredients ingredients)
			throws ResourceNotFoundException {
		List<ReceipeEntity> receipes = reciepeService.getReciepeByIngredients(ingredients);
		LOGGER.info("fetch all receipies by selected ingredients ");
		return new ResponseEntity<>(receipes,
				receipes != null && !receipes.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping("/ingredients")
	public ResponseEntity<List<IngredientsEntity>> getIngredients() {
		List<IngredientsEntity> list = reciepeService.getAllIngredients();

		LOGGER.info("fetch all ingredients ");
		return new ResponseEntity<>(list, list != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PostMapping("/save")
	public ResponseEntity<List<ReceipeEntity>> saveReceipe(@RequestBody List<Receipe> receipe) {
		List<ReceipeEntity> receipeData = reciepeService.saveReciepe(receipe);
		if (receipeData == null) {
			throw new ResourceNotFoundException("you are not able to save data");
		}
		LOGGER.info("save receipe data successfully ");

		return new ResponseEntity<>(receipeData, HttpStatus.CREATED);
	}

}

