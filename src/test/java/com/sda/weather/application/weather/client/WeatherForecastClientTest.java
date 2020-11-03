package com.sda.weather.application.weather.client;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class WeatherForecastClientTest {

    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();

    @Test
    public void test2() {    // todo: name me, develop me
        WeatherResponse response = weatherForecastClient.getWeather(52.0, 12.0);
        System.out.println(WeatherMapper.mapToWeather(response, LocalDate.now()));
    }
}
