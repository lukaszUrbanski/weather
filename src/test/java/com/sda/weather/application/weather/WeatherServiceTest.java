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
        String checkedDate = "2020-01-01";
        LocalDate expectedDate = LocalDate.of(2020, 1, 1);
        // when
        LocalDate resultDate = weatherService.checkDate(checkedDate);

        //then
        assertEquals(expectedDate, resultDate);
    }
    @Test
    void checkDate_shouldReturnCheckedDateWhenCheckedDateIsCorrect() {
        // given
        weatherService.setToday(LocalDate.of(2020, 11, 5));
        String checkedDate = "2020-11-10";
        LocalDate expectedDate = LocalDate.of(2020, 11, 10);
        // when
        LocalDate resultDate = weatherService.checkDate(checkedDate);

        //then
        assertEquals(expectedDate, resultDate);
    }

    @Test
    void checkDate_shouldReturnTomorrowDateWhenCheckedDateIsToFarInFuture() {
        // given
        LocalDate today = LocalDate.of(2020, 11, 5);
        weatherService.setToday(today);
        LocalDate tomorrow = today.plusDays(1);
        String data1 = "2020-11-30";

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
        String data1 = "2020-11-01";
        // when
        LocalDate resultDate = weatherService.checkDate(data1);

        //then
        assertEquals(tomorrow, resultDate);
    }

    @Test
    void checkDate_wrongDateChecking() {
        //given
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        String checkedDate = "12-10-1000";
        //when
        LocalDate resultDate = weatherService.checkDate(checkedDate);

        //then

        assertEquals(expectedDate, resultDate);
    }

    @Test
    void checkDate_WhenDateIdEmpty(){
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        String checkingDate = "";

        LocalDate resultDate = weatherService.checkDate(checkingDate);

        assertEquals(expectedDate, resultDate);
    }



}
