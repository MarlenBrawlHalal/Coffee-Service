package com.example.coffeeservice.services;

import com.example.coffeeservice.entities.DrinkEntity;
import com.example.coffeeservice.entities.IngredientEntity;
import com.example.coffeeservice.entities.RecipeEntity;
import com.example.coffeeservice.handler.DrinkDoesNotExistException;
import com.example.coffeeservice.handler.OutOfIngredientException;
import com.example.coffeeservice.repositories.DrinkRepository;
import com.example.coffeeservice.repositories.IngredientRepository;
import com.example.coffeeservice.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {

  private final IngredientRepository ingredientRepository;
  private final DrinkRepository drinkRepository;
  private final RecipeRepository recipeRepository;

  public void prepareDrink(String drinkName) {
    DrinkEntity drink = drinkRepository.findByName(drinkName);

    if (drink == null) {
      throw new DrinkDoesNotExistException("Drink does not exist");
    }

    List<RecipeEntity> recipeList = recipeRepository.findByDrink(drink);
    for (RecipeEntity recipe : recipeList) {
      String ingredientName = recipe.getIngredient().getName();
      IngredientEntity ingredient = ingredientRepository.findByName(ingredientName);

      if (ingredient.getQuantity() - recipe.getAmount() < 0) {
        throw new OutOfIngredientException("Not enough " + ingredientName + " for " + drinkName);
      }

      ingredient.setQuantity(ingredient.getQuantity() - recipe.getAmount());
      ingredientRepository.save(ingredient);
    }
  }
}
