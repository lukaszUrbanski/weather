package com.sda.weather.application.weather;

import com.sda.weather.application.location.LocationService;

public class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();
    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();

    public Weather showWeatherInformation(final String cityName) {
        if (isLocationExist(cityName)) {
           // WeatherResponse weather = weatherForecastClient.getWeather(cityName);
            return weatherRepository.saveWeather(new Weather());
        } else {
            System.out.println("Location incorrect");
            throw new RuntimeException("...");              // todo
        }
    }

    public boolean isLocationExist(final String cityName) {
        return locationService.isLocationExist(cityName);
    }
}
