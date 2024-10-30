package com.example.coffeeservice.repositories;

import com.example.coffeeservice.entities.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Integer> {
  DrinkEntity findByName(String name);
}
