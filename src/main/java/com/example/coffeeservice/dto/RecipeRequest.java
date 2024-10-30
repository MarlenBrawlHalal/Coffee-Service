package com.example.coffeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequest {

  private String drinkName;
  private Map<String, Integer> ingredients;
}
