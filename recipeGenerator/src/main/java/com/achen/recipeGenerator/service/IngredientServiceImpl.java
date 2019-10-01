package com.achen.recipeGenerator.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.achen.recipeGenerator.models.Ingredient;
import com.achen.recipeGenerator.repositories.IngredientRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service("ingredientService")
public class IngredientServiceImpl implements IngredientService {
	private Gson gson = new GsonBuilder().create();

	@Autowired
	SequenceGeneratorService sequenceGenerator;

	@Autowired
	IngredientRepo ingredientRepo;

	@Override
	public ResponseEntity<?> addIngredientText(String ingredientName, String userId) {
		try {
			Date dateobj = new Date();

			Ingredient newIngredient = new Ingredient();
			newIngredient.setUserId(userId);
			newIngredient.setDate(dateobj);
			newIngredient.setIngredientName(ingredientName);

			// TODO: there should be no ingredient that is the same for each user. Right now if ingredient exists, it is overrided
			ingredientRepo.save(newIngredient);

			return new ResponseEntity<>(String.format("Ingredient %s saved", newIngredient), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getIngredientByName(String name) {
		List<Ingredient> ingredients = ingredientRepo.findAllByName(name);

		if (ingredients.size() != 1) {
			// error that none or too much are found
			return new ResponseEntity<>(String.format("Could not find: %s", name), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(gson.toJson(ingredients.get(0)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllIngredientsByUser(String userId) {
		try {
			List<Ingredient> ingredients = ingredientRepo.findAllByUserId(userId);
			return new ResponseEntity<>(gson.toJson(ingredients), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}
}
