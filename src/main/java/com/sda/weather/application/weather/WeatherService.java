package com.sda.weather.application.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.application.location.LocationService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

public class WeatherService {

    private final WeatherRepository weatherRepository = new WeatherRepository();
    private final LocationService locationService = new LocationService();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String accessKey = "814e951ff41c347812903ae501d2e573"; // API access key for http://api.weatherstack.com/

    public WeatherService() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public Weather showWeatherInformation(final String cityName) {
        if (isLocationExist(cityName)) {
            checkLocationWeather(cityName);
            weatherRepository.checkLocationWeather(cityName);
        } else {
            System.out.println("Location incorrect");
        }

        return null; // todo
    }

    public Weather getWeather() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://api.weatherstack.com/current"))
                .build();
        try {

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();

            Weather weatherResponse = objectMapper.readValue(responseBody, Weather.class);

            return weatherResponse;
        } catch (Exception e) {
           System.out.println("Nie udana próba pobrania pogody" + e.getStackTrace());
           return new Weather();
        }

    }


    private void checkLocationWeather(final String cityName) {


    }

    public boolean isLocationExist(final String cityName) {
        return locationService.isLocationExist(cityName);
    }
}
