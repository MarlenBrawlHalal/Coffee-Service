package com.example.coffeeservice.controllers;

import com.example.coffeeservice.dto.*;
import com.example.coffeeservice.services.CoffeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coffee")
@RequiredArgsConstructor
@Tag(name = "Coffee")
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

  @PostMapping("/ingredients")
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

  @GetMapping("/popular-drink")
  public ResponseEntity<DrinkResponse> getMostPopularDrink() {
    return new ResponseEntity<>(coffeeService.getMostPopularDrink(), HttpStatus.OK);
  }
}
