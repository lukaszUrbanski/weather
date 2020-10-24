package com.sda.weather.application.weather;

public class WeatherController {

    private final WeatherService weatherService = new WeatherService();

    // todo: return something
    public String showWeatherInformation(final String cityName) {
        Weather weather = weatherService.showWeatherInformationByLocation(cityName);
        return weather.toString();
    }

    public String showWeatherInformation(final double latitude, final double longitude) {
        Weather weather = weatherService.showWeatherInformationByCoordinates(latitude, longitude);
        return weather.toString();
    }
}
