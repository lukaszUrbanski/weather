package com.sda.weather.application.weather;

import com.sda.weather.application.location.LocationService;
import com.sda.weather.application.request_response.WeatherForecastClient;
import com.sda.weather.application.request_response.WeatherResponse;

public class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();
    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();

    public Weather showWeatherInformation(final String cityName) {
        if (isLocationExist(cityName)) {
          WeatherResponse weatherResponse = weatherForecastClient.getWeather(cityName);
          Weather weather = weatherResponse.getWeather(cityName);
            return weatherRepository.saveWeather(weather);
        } else {
            System.out.println("Location incorrect");
            throw new RuntimeException("...");              // todo
        }
    }

    public boolean isLocationExist(final String cityName) {
        return locationService.isLocationExist(cityName);
    }
}
