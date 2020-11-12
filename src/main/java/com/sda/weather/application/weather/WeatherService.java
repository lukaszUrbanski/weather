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

            LocalDate weatherDate = checkDate(date);

            Weather weather = getWeather(latitude, longitude, weatherDate);

            weather.setLocation(location);

            return saveWeather(weather);
        } else {
            LocationClient locationClient = new LocationClient();

            NewLocation newLocation = locationClient.getLocation(cityName);
            Location location = LocationMapper.mapToLocation(newLocation);

            locationRepository.saveNewLocation(location);
            return showWeatherInformation(cityName, date);
        }
    }

    Weather showWeatherInformation(final Double latitude, Double longitude, final String date) {
        LocalDate weatherDate = checkDate(date);

        if (CoordinatesValidator.isCoordinatesCorrect(latitude, longitude)) {
            Weather weather = getWeather(latitude, longitude, weatherDate);

            return saveWeather(weather);
        } else {
            throw new WrongDataException("Wrong coordinates");
        }
    }

    private Weather getWeather(final Double latitude, final Double longitude, final LocalDate weatherDate) {
        WeatherResponse weatherResponse = weatherForecastClient.getWeather(latitude, longitude);
        return WeatherMapper.mapToWeather(weatherResponse, weatherDate);
    }

    private Weather saveWeather(final Weather weather) {
        if (isWeatherCorrect(weather)) {
            return weatherRepository.saveWeather(weather);
        } else
            throw new WrongDataException("Weather is incorrect.");
    }

    LocalDate checkDate(String date) {
        LocalDate weatherDate;
        try {
            weatherDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(datePattern));
        } catch (Exception e) {
            return LocalDate.now().plusDays(1);
            // throw new WrongDataException("Wrong date " + e.getStackTrace().toString()); // todo log exception
        }

        if (weatherDate.toEpochDay() - today.toEpochDay() <= 0 || weatherDate.toEpochDay() - today.toEpochDay() >= 7) {
            weatherDate = today.plusDays(1);
        }
        return weatherDate;
    }

    private boolean isWeatherCorrect(final Weather weather) {
        boolean isCorrect = true;
        if (weather.humidity < 0 || weather.humidity > 100) {
            isCorrect = false;
        }

        if (weather.pressure < 800 || weather.pressure > 1100) {
            isCorrect = false;
        }

        if (weather.temperature < -100 || weather.temperature > 65) {
            isCorrect = false;
        }

        if ( Integer.parseInt(weather.windDirectory) < 0 || Integer.parseInt(weather.windDirectory) > 360){
            isCorrect = false;
        }

        if (weather.windSpeed < 0 || weather.windSpeed > 500){
            isCorrect = false;
        }

        return isCorrect;
    }


    private boolean isLocationExist(final String cityName) {
        return locationService.isLocationExist(cityName);
    }
}
