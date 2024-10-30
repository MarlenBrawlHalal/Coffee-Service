package com.example.coffeeservice.services;

import com.example.coffeeservice.dto.*;
import com.example.coffeeservice.entities.DrinkEntity;
import com.example.coffeeservice.entities.IngredientEntity;
import com.example.coffeeservice.entities.RecipeEntity;
import com.example.coffeeservice.handler.DrinkAlreadyExistsException;
import com.example.coffeeservice.handler.DrinkDoesNotExistException;
import com.example.coffeeservice.handler.IngredientDoesNotExistException;
import com.example.coffeeservice.handler.OutOfIngredientException;
import com.example.coffeeservice.repositories.DrinkRepository;
import com.example.coffeeservice.repositories.IngredientRepository;
import com.example.coffeeservice.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    drink.setOrderCount(drink.getOrderCount() + 1);
    drinkRepository.save(drink);

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

  public void addDrink(DrinkRequest drinkRequest) {
    DrinkEntity drink = drinkRepository.findByName(drinkRequest.getName());

    if (drink != null) {
      throw new DrinkAlreadyExistsException(drink.getName() + " already exists");
    }

    DrinkEntity newDrink = new DrinkEntity(drinkRequest.getName());
    drinkRepository.save(newDrink);
  }

  public void addIngredient(IngredientRequest ingredientRequest) {

    IngredientEntity foundIngredient = ingredientRepository.findByName(ingredientRequest.getName());
    if (foundIngredient != null) {
      int amount = foundIngredient.getQuantity();
      foundIngredient.setQuantity(amount + ingredientRequest.getQuantity());
      ingredientRepository.save(foundIngredient);
    } else {
      IngredientEntity newIngredient = new IngredientEntity(ingredientRequest.getName(), ingredientRequest.getQuantity());
      ingredientRepository.save(newIngredient);
    }
  }

  public void addRecipe(RecipeRequest recipeRequest) {
    DrinkEntity drink = drinkRepository.findByName(recipeRequest.getDrinkName());

    if (drink == null) {
      throw new DrinkDoesNotExistException("Drink " + recipeRequest.getDrinkName() + " doesn't exist");
    }

    List<RecipeEntity> recipes = recipeRepository.findByDrink(drink);
    recipeRepository.deleteAll(recipes);

    for (Map.Entry<String, Integer> entry : recipeRequest.getIngredients().entrySet()) {
      RecipeEntity newRecipe = new RecipeEntity();
      newRecipe.setDrink(drink);
      IngredientEntity ingredient = ingredientRepository.findByName(entry.getKey());

      if (ingredient == null) {
        throw new IngredientDoesNotExistException(entry.getKey() + " doesn't exist");
      }
      newRecipe.setIngredient(ingredient);
      newRecipe.setAmount(entry.getValue());
      recipeRepository.save(newRecipe);
    }
  }

  public List<IngredientResponse> getIngredients() {

    List<IngredientResponse> ingredients = new ArrayList<>();
    List<IngredientEntity> ingredientEntities = ingredientRepository.findAll();

    for (IngredientEntity ingredient : ingredientEntities) {
        ingredients.add(new IngredientResponse(ingredient.getName(), ingredient.getQuantity()));
    }

    return ingredients;
  }

  public DrinkResponse getMostPopularDrink() {

//    List<DrinkEntity> drinkEntityList = drinkRepository.findAll();
//    int max_order_count = -1;
//
//    for (DrinkEntity drink : drinkEntityList) {
//      if (drink.getOrderCount() < max_order_count) {
//        max_order_count = drink.getOrderCount();
//      }
//    }
    DrinkEntity drink = drinkRepository.findTopByOrderByOrderCountDesc();
    return new DrinkResponse(drink.getName());
  }
}
