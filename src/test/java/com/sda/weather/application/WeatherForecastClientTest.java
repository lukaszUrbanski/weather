package com.sda.weather.application;

import com.sda.weather.application.request_response.WeatherForecastClient;
import com.sda.weather.application.request_response.WeatherResponse;
import org.junit.jupiter.api.Test;

public class WeatherForecastClientTest {

    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();

    @Test
    public void test() {    // todo: name me
        WeatherResponse response = weatherForecastClient.getWeather("London");
        System.out.println(response);
    }
}
