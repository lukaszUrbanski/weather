package com.sda.weather.application.weather;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherServiceTest {

    WeatherService weatherService = new WeatherService();

    @Test
    void test_1() {    // todo name me
        // given
        String date = LocalDate.now().plus(2, ChronoUnit.DAYS).toString();

        // when
        Weather weather = weatherService.showWeatherInformation("London", date);

        // then
        assertEquals(weather.location.getCity(), "London");
        // todo add more assertions
    }

    @Test
    void test_2() {
        // given
        String date = LocalDate.now().plus(8, ChronoUnit.DAYS).toString();

        // when
        Weather weather = weatherService.showWeatherInformation("London", date);

        // todo java.lang.IndexOutOfBoundsException: Index 8 out of bounds for length 8
        // todo 'LocalDate checkDate(LocalDate weatherDate)' doesn't work
    }
}
