package com.sda.weather.application.weather;

import com.sda.weather.application.WrongDataException;
import com.sda.weather.application.location.Location;
import com.sda.weather.application.location.LocationRepository;
import com.sda.weather.application.location.LocationService;
import com.sda.weather.application.weather.client.WeatherForecastClient;
import com.sda.weather.application.weather.client.WeatherMapper;
import com.sda.weather.application.weather.client.WeatherResponse;
import com.sda.weather.application.weather.client.WeatherResponseByCoordinates;

public class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();
    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();
    private final LocationRepository locationRepository = new LocationRepository();

    public Weather showWeatherInformationByLocation(final String cityName) {
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

    public Weather showWeatherInformationByCoordinates(final Double latitude, Double longitude) {
        if (isCoordinatesCorrect(latitude, longitude)) {
            WeatherResponseByCoordinates weatherResponseByCoordinates = weatherForecastClient.getWeather(latitude, longitude);
           Weather weather = WeatherMapper.mapToWeather(weatherResponseByCoordinates);

            return weatherRepository.saveWeather(weather);
        } else {
            System.out.println("Location incorrect");
            //todo add "would you like save location? "
            throw new RuntimeException("...");              // todo
        }
    }

    private boolean isCoordinatesCorrect(final Double latitude, final Double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new WrongDataException("Latitude is wrong.");
        }

        if (longitude < -180 || longitude > 180) {
            throw new WrongDataException("Longitude is wrong.");
        }
        return true;
    }

    public boolean isLocationExist(final String cityName) {
        return locationService.isLocationExist(cityName);
    }

}
