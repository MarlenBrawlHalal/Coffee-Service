package com.example.coffeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CoffeeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoffeeServiceApplication.class, args);
  }

}
