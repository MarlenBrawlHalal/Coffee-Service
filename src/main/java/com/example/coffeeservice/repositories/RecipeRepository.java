package com.example.coffeeservice.repositories;

import com.example.coffeeservice.entities.DrinkEntity;
import com.example.coffeeservice.entities.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

  List<RecipeEntity> findByDrink(DrinkEntity drinkEntity);
}
