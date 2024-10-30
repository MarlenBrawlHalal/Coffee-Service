package com.example.coffeeservice.repositories;

import com.example.coffeeservice.entities.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer> {
  IngredientEntity findByName(String name);
}
