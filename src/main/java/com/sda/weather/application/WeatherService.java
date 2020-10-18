package com.sda.weather.application;

public class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();

    public Weather showWeatherInformation(final String cityName) {
        if (isLocationExist(cityName)) {
            weatherRepository.checkLocationWeather(cityName);
        } else {
            System.out.println("Location incorrect");
        }

        return null; // todo
    }

    public boolean isLocationExist(final String cityName) {
        return locationService.isLocationExist(cityName);
    }
}
