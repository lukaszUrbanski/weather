package com.sda.weather.application;

import com.sda.weather.application.weather.WeatherForecastClient;
import com.sda.weather.application.weather.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

public class WeatherForecastClientTest {

    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();

    @Test
    public void test() {    // todo: name me
        WeatherResponse response = weatherForecastClient.getWeather("London");
        System.out.println(response);
    }
}
