package com.sda.weather.application.weather;

import com.sda.weather.application.WrongDataException;
import com.sda.weather.application.location.Location;
import com.sda.weather.application.location.LocationRepository;
import com.sda.weather.application.location.LocationService;
import com.sda.weather.application.location.client.LocationClient;
import com.sda.weather.application.location.client.LocationMapper;
import com.sda.weather.application.location.client.NewLocation;
import com.sda.weather.application.weather.client.WeatherForecastClient;
import com.sda.weather.application.weather.client.WeatherMapper;
import com.sda.weather.application.weather.client.WeatherResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();
    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();
    private final LocationRepository locationRepository = new LocationRepository();
    private final String datePattern = "yyyy-MM-dd";

    Weather showWeatherInformation(final String cityName, final String date) {
        if (isLocationExist(cityName)) {
            Location location = locationRepository.getLocation(cityName);
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            LocalDate weatherDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(datePattern));
            weatherDate = checkDate(weatherDate);

            WeatherResponse weatherResponse = weatherForecastClient.getWeather(latitude, longitude);

            Weather weather = WeatherMapper.mapToWeather(weatherResponse, weatherDate);
            weather.setLocation(location);

            return weatherRepository.saveWeather(weather);

        } else {
            LocationClient locationClient = new LocationClient();
            LocationMapper locationMapper = new LocationMapper();

            NewLocation newLocation = locationClient.getLocation(cityName);
            Location location = locationMapper.mapToLocation(newLocation);

            locationRepository.saveNewLocation(location);
            return showWeatherInformation(cityName, date);
        }
    }

    private LocalDate checkDate(LocalDate weatherDate) {
        // todo weatherDate.getDayOfYear() for 2021.01.02
        // todo LocalDate.now().getDayOfYear() for 2020.12.31
        // todo use LocalDate API
        if (weatherDate.getDayOfYear() - LocalDate.now().getDayOfYear() <= 0 && weatherDate.getDayOfYear() - LocalDate.now().getDayOfYear() >= 7) {
            weatherDate = LocalDate.now(); // todo tomorrow?
        }
        return weatherDate;
    }


    Weather showWeatherInformation(final Double latitude, Double longitude, final String date) {
        LocalDate weatherDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (isCoordinatesCorrect(latitude, longitude)) {
            WeatherResponse weatherResponse = weatherForecastClient.getWeather(latitude, longitude);
            Weather weather = WeatherMapper.mapToWeather(weatherResponse, weatherDate);

            // todo valid a localization and fill in to the weather

            return weatherRepository.saveWeather(weather);
        } else {
            //todo add "would you like save location? "
            throw new RuntimeException("...");              // todo
        }
    }

    private boolean isCoordinatesCorrect(final Double latitude, final Double longitude) {
        if (latitude < -90 || latitude > 90) {                      // todo duplicate with the code from LocationService -> move these code to separate service (eg. CoordinatesValidator.java)
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
