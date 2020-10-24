package com.sda.weather.application.weather.client;

import org.junit.jupiter.api.Test;

public class WeatherForecastClientTest {

    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();

    @Test
    public void test() {    // todo: name me
        WeatherResponse response = weatherForecastClient.getWeather("London");
        System.out.println(response);
    }

    @Test
    public void test2() {    // todo: name me
        WeatherCoordinatesResponse response = weatherForecastClient.getWeather(52.0, 12.0);
        System.out.println(response);
    }
}
