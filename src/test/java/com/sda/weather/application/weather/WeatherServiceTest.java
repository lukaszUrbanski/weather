package com.sda.weather.application.weather;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherServiceTest {

    WeatherService weatherService = new WeatherService();

    @Test
    void checkDate_shouldReturnCheckedDateWhenCheckNewYearDate() {
        // given
        weatherService.setToday(LocalDate.of(2019, 12, 31));
        LocalDate data1 = LocalDate.of(2020, 1, 1);

        // when
        LocalDate resultDate = weatherService.checkDate(data1);

        //then
        assertEquals(data1, resultDate);
    }
    @Test
    void checkDate_shouldReturnCheckedDateWhenCorrectDate() {
        // given
        weatherService.setToday(LocalDate.of(2020, 11, 5));
        LocalDate data1 = LocalDate.of(2020, 11, 10);

        // when
        LocalDate resultDate = weatherService.checkDate(data1);

        //then
        assertEquals(data1, resultDate);
    }

    @Test
    void checkDate_shouldReturnTomorrowDateWhenCheckedDateIsToFarInFuture() {
        // given
        LocalDate today = LocalDate.of(2020, 11, 5);
        weatherService.setToday(today);
        LocalDate tomorrow = today.plusDays(1);
        LocalDate data1 = LocalDate.of(2020, 11, 30);

        // when
        LocalDate resultDate = weatherService.checkDate(data1);

        //then
        assertEquals(tomorrow, resultDate);
    }

    @Test
    void checkDate_shouldReturnTomorrowDateWhenCheckedDateIsInThePast() {
        // given
        LocalDate today = LocalDate.of(2020, 11, 5);
        LocalDate tomorrow = today.plusDays(1);
        weatherService.setToday(today);
        LocalDate data1 = LocalDate.of(2020, 11, 1);

        // when
        LocalDate resultDate = weatherService.checkDate(data1);

        //then
        assertEquals(tomorrow, resultDate);
    }

//    @Test
//    void checkDate_wrongDateChecking() {
//        //given
//        LocalDate date1 = LocalDate.of(2020, 13, 1);
//        LocalDate expectedDate = LocalDate.now().plusDays(1);
//
//        //when
//        LocalDate resultDate = weatherService.checkDate(date1);
//
//        //then
//
//        assertEquals(expectedDate, resultDate);
//    }



}
