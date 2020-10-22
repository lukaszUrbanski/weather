package com.sda.weather.application.weather;

import com.sda.weather.application.location.Location;
import com.sda.weather.application.location.LocationRepository;
import com.sda.weather.application.location.LocationService;
import com.sda.weather.application.weather.client.WeatherForecastClient;
import com.sda.weather.application.weather.client.WeatherMapper;
import com.sda.weather.application.weather.client.WeatherResponse;

public class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();
    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();
    private final LocationRepository locationRepository = new LocationRepository();

    public Weather showWeatherInformation(final String cityName) {
        if (isLocationExist(cityName)) {
            WeatherResponse weatherResponse = weatherForecastClient.getWeather(cityName);
            Weather weather = WeatherMapper.mapToWeather(weatherResponse);
            Location location = locationRepository.getLocation(cityName);
            weather.setLocation(location);

            return weatherRepository.saveWeather(weather);
        } else {
            System.out.println("Location incorrect");
            //todo add "would you like save location? "
            throw new RuntimeException("...");              // todo
        }
    }

    public boolean isLocationExist(final String cityName) {
        return locationService.isLocationExist(cityName);
    }
}
