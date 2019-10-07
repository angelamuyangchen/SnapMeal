package com.achen.recipeGenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.achen.recipeGenerator.models.ImageRequestDto;
import com.achen.recipeGenerator.service.IngredientService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@RequestMapping(value="/getIngredientByName/{ingredientName}", method = RequestMethod.GET)
	public ResponseEntity<?> getIngredientByName(@PathVariable String ingredientName) {
		return ingredientService.getIngredientByName(ingredientName);
	}
	
	@RequestMapping(value="/getAllIngredientsByUser/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllIngredientsByUser(@PathVariable String userId) {
		return ingredientService.getAllIngredientsByUser(userId);
	}
	
	@RequestMapping(value="/addIngredientFromText/{ingredientName}/{userId}", method = RequestMethod.POST)
	public ResponseEntity<?> addIngredientsText(@PathVariable("ingredientName") String ingredientName, @PathVariable("userId") String userId) {
		return ingredientService.addIngredientFromText(ingredientName, userId);
	}
	
	@RequestMapping(value="/addIngredientFromImage/", method = RequestMethod.POST)
	public ResponseEntity<?> addIngredientImage(@RequestBody ImageRequestDto imageProp) {
		System.out.println(imageProp);
		return ingredientService.addIngredientFromImage(imageProp);
	}
}
