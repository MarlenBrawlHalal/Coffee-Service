package com.example.coffeeservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class RecipeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "drink_id", nullable = false)
  private DrinkEntity drink;

  @ManyToOne
  @JoinColumn(name = "ingredient_id", nullable = false)
  private IngredientEntity ingredient;

  private int amount;
}
