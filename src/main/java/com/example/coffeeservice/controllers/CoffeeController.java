package com.example.coffeeservice.controllers;

import com.example.coffeeservice.services.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coffee")
@RequiredArgsConstructor
public class CoffeeController {

  private final CoffeeService coffeeService;

  @PostMapping("/prepare/{drinkName}")
  public ResponseEntity<String> prepareDrink(@PathVariable String drinkName) {
    coffeeService.prepareDrink(drinkName);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
