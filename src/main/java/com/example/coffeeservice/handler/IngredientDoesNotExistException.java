package com.example.coffeeservice.handler;

public class IngredientDoesNotExistException extends RuntimeException {
  public IngredientDoesNotExistException(String message) {
    super(message);
  }

  public IngredientDoesNotExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
