package com.sda.weather.application.weather;

public class WeatherController {

    private final WeatherService weatherService = new WeatherService();

    // todo: return something
    public String showWeatherInformation(final String cityName) {
        Weather weather = weatherService.showWeatherInformation(cityName);
        return weather.toString();
    }
}
