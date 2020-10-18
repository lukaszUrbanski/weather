package com.sda.weather.application;

public class WeatherController {

    private final WeatherService weatherService = new WeatherService();

    public void findLocation(final String cityName) {
        weatherService.findWeatherLocation(cityName);
    }
}
