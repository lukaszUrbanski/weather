package com.sda.weather.application.weather;

import com.sda.weather.application.CoordinatesValidator;
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
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();
    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();
    private final LocationRepository locationRepository = new LocationRepository();
    private final String datePattern = "yyyy-MM-dd";
    private LocalDate today = LocalDate.now();

    Weather showWeatherInformation(final String cityName, final String date) {
        if (isLocationExist(cityName)) {
            Location location = locationRepository.getLocation(cityName);
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            LocalDate weatherDate;
            try {
                weatherDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(datePattern));
            } catch (Exception e) {
                weatherDate = LocalDate.now().plusDays(1);
                // throw new WrongDataException("Wrong date " + e.getStackTrace().toString()); // todo log exception
            }
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


    public LocalDate checkDate(LocalDate weatherDate) {

        if (weatherDate.toEpochDay() - today.toEpochDay() <= 0 || weatherDate.toEpochDay() - today.toEpochDay() >= 7) {
            weatherDate = today.plusDays(1);
        }
        return weatherDate;
    }


    Weather showWeatherInformation(final Double latitude, Double longitude, final String date) {
        LocalDate weatherDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (CoordinatesValidator.isCoordinatesCorrect(latitude, longitude)) {
            WeatherResponse weatherResponse = weatherForecastClient.getWeather(latitude, longitude);
            Weather weather = WeatherMapper.mapToWeather(weatherResponse, weatherDate);

            if (isWeatherCorrect(weather)) {
                return weatherRepository.saveWeather(weather);
            } else
                throw new RuntimeException("...");
        } else {
            //todo add "would you like save location? "
            throw new RuntimeException("...");              // todo
        }
    }

    private boolean isWeatherCorrect(final Weather weather) {
        // todo develop method
        return true;
    }


    public boolean isLocationExist(final String cityName) {
        return locationService.isLocationExist(cityName);
    }
}
