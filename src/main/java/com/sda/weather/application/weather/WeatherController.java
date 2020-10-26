package com.sda.weather.application.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.application.InternalServerException;

public class WeatherController {

    private final WeatherService weatherService = new WeatherService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String showWeatherInformation(final String cityName, final String date) {
        Weather weather = weatherService.showWeatherInformation(cityName, date);
        return getString(weather);
    }

    public String showWeatherInformation(final double latitude, final double longitude, final String date ) {
        Weather weather = weatherService.showWeatherInformation(latitude, longitude, date);
        return getString(weather);
    }


    private String getString(final Weather weather) {
        try {
            return objectMapper.writeValueAsString(weather);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("Data cant't be serialised.");
        }
    }
}
