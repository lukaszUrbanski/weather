package com.sda.weather.application;

public class WeatherService {
    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();

    public void findWeatherLocation(final String cityName) {

        locationService.isLocationExist(cityName);

    }
}
