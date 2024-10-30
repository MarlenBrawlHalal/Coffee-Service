package com.example.coffeeservice.holidayService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class HolidayService {

  private final String HOLIDAYS_API_URL = "https://date.nager.at/api/v3/publicholidays/{year}/{countryCode}";

  @Cacheable("holidays")
  public Set<LocalDate> getHolidays(int year, String countryCode) {
    RestTemplate restTemplate = new RestTemplate();
    Holiday[] holidays = restTemplate.getForObject(HOLIDAYS_API_URL, Holiday[].class, year, countryCode);

    Set<LocalDate> holidayDates = new HashSet<>();
    if (holidays != null) {
      for (Holiday holiday : holidays) {
        holidayDates.add(holiday.getDate());
      }
    }

    return holidayDates;
  }
}
