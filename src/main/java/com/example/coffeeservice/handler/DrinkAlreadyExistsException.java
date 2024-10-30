package com.example.coffeeservice.handler;

public class DrinkAlreadyExistsException extends RuntimeException {
  public DrinkAlreadyExistsException(String message) {
    super(message);
  }

  public DrinkAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
