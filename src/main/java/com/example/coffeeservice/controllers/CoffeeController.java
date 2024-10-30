package com.example.coffeeservice.controllers;

import com.example.coffeeservice.dto.DrinkRequest;
import com.example.coffeeservice.dto.IngredientRequest;
import com.example.coffeeservice.dto.IngredientResponse;
import com.example.coffeeservice.dto.RecipeRequest;
import com.example.coffeeservice.services.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coffee")
@RequiredArgsConstructor
public class CoffeeController {

  private final CoffeeService coffeeService;

  @PostMapping("/prepare")
  public ResponseEntity<String> prepareDrink(@RequestParam(value = "name") String drinkName) {
    coffeeService.prepareDrink(drinkName);
    return new ResponseEntity<>("Preparing " + drinkName, HttpStatus.OK);
  }

  @PostMapping("/drink")
  public ResponseEntity<Void> addDrink(@RequestBody DrinkRequest drinkRequest) {
   coffeeService.addDrink(drinkRequest);
   return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/ingredient")
  public ResponseEntity<Void> addIngredient(@RequestBody IngredientRequest ingredientRequest) {
    coffeeService.addIngredient(ingredientRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/recipe")
  public ResponseEntity<Void> addRecipe(@RequestBody RecipeRequest recipeRequest) {
    coffeeService.addRecipe(recipeRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/ingredients")
  public ResponseEntity<List<IngredientResponse>> getIngredients() {
    return new ResponseEntity<>(coffeeService.getIngredients(), HttpStatus.OK);
  }
}
