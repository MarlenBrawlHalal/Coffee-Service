package com.example.coffeeservice.holidayService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {

  private LocalDate date;
  private String localName;
  private String name;
  private String countryCode;
  private boolean fixed;
  private boolean global;
  private List<String> counties;
  private Integer launchYear;
  private List<String> types;

  @Override
  public String toString() {
    return "Holiday{" +
        "date=" + date +
        ", localName='" + localName + '\'' +
        ", name='" + name + '\'' +
        ", countryCode='" + countryCode + '\'' +
        ", fixed=" + fixed +
        ", global=" + global +
        ", counties=" + counties +
        ", launchYear=" + launchYear +
        ", types=" + types +
        '}';
  }
}
