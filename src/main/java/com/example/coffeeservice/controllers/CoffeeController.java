package com.example.coffeeservice.controllers;

import com.example.coffeeservice.dto.IngredientRequest;
import com.example.coffeeservice.entities.IngredientEntity;
import com.example.coffeeservice.services.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coffee")
@RequiredArgsConstructor
public class CoffeeController {

  private final CoffeeService coffeeService;

  @PostMapping("/prepare/{drinkName}")
  public ResponseEntity<Void> prepareDrink(@PathVariable String drinkName) {
    coffeeService.prepareDrink(drinkName);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/ingredient")
  public ResponseEntity<Void> addIngredient(@RequestBody IngredientRequest ingredientRequest) {
    coffeeService.addIngredient(ingredientRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
