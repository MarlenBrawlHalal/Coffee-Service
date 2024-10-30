package com.example.coffeeservice.handler;

public class CoffeeServiceIsNotAvailableException extends RuntimeException {
  public CoffeeServiceIsNotAvailableException(String message) {
    super(message);
  }
  public CoffeeServiceIsNotAvailableException(String message, Throwable cause) {
    super(message, cause);
  }
}
