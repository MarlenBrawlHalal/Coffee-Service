package com.example.coffeeservice.handler;

public class DrinkDoesNotExistException extends RuntimeException {
  public DrinkDoesNotExistException(String message) {
    super(message);
  }
  public DrinkDoesNotExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
