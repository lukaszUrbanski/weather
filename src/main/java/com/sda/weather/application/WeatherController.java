package com.sda.weather.application;

public class WeatherController {

    private final WeatherService weatherService = new WeatherService();

    public void showWeatherInformation(final String cityName) {
        Weather weather = weatherService.showWeatherInformation(cityName);
    }
}
