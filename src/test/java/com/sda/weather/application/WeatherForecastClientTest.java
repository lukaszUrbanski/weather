package com.sda.weather.application;

import com.sda.weather.application.weather.client.WeatherForecastClient;
import com.sda.weather.application.weather.client.WeatherResponse;
import com.sda.weather.application.weather.client.WeatherResponseByCoordinates;
import org.junit.jupiter.api.Test;

public class WeatherForecastClientTest {

    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();

    @Test
    public void test() {    // todo: name me
        WeatherResponseByCoordinates response = weatherForecastClient.getWeather(52.0, 12.0);
        System.out.println(response);
    }
}
