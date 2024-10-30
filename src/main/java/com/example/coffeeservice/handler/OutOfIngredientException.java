package com.example.coffeeservice.handler;

public class OutOfIngredientException extends RuntimeException {
  public OutOfIngredientException(String message) {
    super(message);
  }
  public OutOfIngredientException(String message, Throwable cause) {
    super(message, cause);
  }
}
