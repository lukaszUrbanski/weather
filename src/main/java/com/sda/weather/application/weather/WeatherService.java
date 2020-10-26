package com.sda.weather.application.weather;

import com.sda.weather.application.WrongDataException;
import com.sda.weather.application.location.Location;
import com.sda.weather.application.location.LocationRepository;
import com.sda.weather.application.location.LocationService;
import com.sda.weather.application.location.client.NewLocation;
import com.sda.weather.application.location.client.LocationClient;
import com.sda.weather.application.location.client.LocationMapper;
import com.sda.weather.application.weather.client.WeatherForecastClient;
import com.sda.weather.application.weather.client.WeatherMapper;
import com.sda.weather.application.weather.client.WeatherResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();
    private final WeatherForecastClient weatherForecastClient = new WeatherForecastClient();
    private final LocationRepository locationRepository = new LocationRepository();

    Weather showWeatherInformation(final String cityName, final String date)  {
        if (isLocationExist(cityName)) {
            Location location = locationRepository.getLocation(cityName);
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            LocalDate weatherDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (weatherDate.getDayOfYear() - LocalDate.now().getDayOfYear() <= 0 &&weatherDate.getDayOfYear() - LocalDate.now().getDayOfYear() >= 7 ){
                System.out.println("Wrong data. The weather will be shown for tomorrow.");
                weatherDate = LocalDate.now();
            }

            WeatherResponse weatherResponse = weatherForecastClient.getWeather(latitude, longitude);

            Weather weather = WeatherMapper.mapToWeather(weatherResponse, weatherDate);
            weather.setLocation(location);

            return weatherRepository.saveWeather(weather);

        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Location incorrect");

            System.out.println("Would you like add this location? Y/N ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("Y")) {
                LocationClient locationClient = new LocationClient();
                LocationMapper locationMapper = new LocationMapper();

                NewLocation newLocation = locationClient.getLocation(cityName);
                Location location = locationMapper.mapToLocation(newLocation);

                locationRepository.saveNewLocation(location);
                return showWeatherInformation(cityName, date);
            } else {
                System.out.println("Enter new location or close application");
                String newLocation = scanner.nextLine();
                return showWeatherInformation(newLocation, date);
            }
        }
    }


    Weather showWeatherInformation(final Double latitude, Double longitude, final String date) {
        LocalDate weatherDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (isCoordinatesCorrect(latitude, longitude)) {
            WeatherResponse weatherResponse = weatherForecastClient.getWeather(latitude, longitude);
            Weather weather = WeatherMapper.mapToWeather(weatherResponse, weatherDate);

            // todo valid a localization and fill in to the weather

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
