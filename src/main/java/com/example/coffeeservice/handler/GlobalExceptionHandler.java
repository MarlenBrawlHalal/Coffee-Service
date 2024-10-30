package com.example.coffeeservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(DrinkDoesNotExistException.class)
  public ResponseEntity<ExceptionResponse> handleException(DrinkDoesNotExistException exp) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(
            ExceptionResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorDescription("Bad request")
                .error(exp.getMessage())
                .build()
        );
  }

  @ExceptionHandler(DrinkAlreadyExistsException.class)
  public ResponseEntity<ExceptionResponse> handleException(DrinkAlreadyExistsException exp) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(
            ExceptionResponse.builder()
                .errorCode(HttpStatus.CONFLICT.value())
                .errorDescription("Drink already exists")
                .error(exp.getMessage())
                .build()
        );
  }

  @ExceptionHandler(OutOfIngredientException.class)
  public ResponseEntity<ExceptionResponse> handleException(OutOfIngredientException exp) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(
            ExceptionResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorDescription("Required ingredient is missing")
                .error(exp.getMessage())
                .build()
        );
  }

  @ExceptionHandler(CoffeeServiceIsNotAvailableException.class)
  public ResponseEntity<ExceptionResponse> handleException(CoffeeServiceIsNotAvailableException exp) {
    return ResponseEntity
        .status(HttpStatus.SERVICE_UNAVAILABLE)
        .body(
            ExceptionResponse.builder()
                .errorCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .errorDescription("Service is unavailable")
                .error(exp.getMessage())
                .build()
        );
  }

}

