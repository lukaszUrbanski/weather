package com.sda.weather.application.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherForecastClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String ACCESS_KEY = "814e951ff41c347812903ae501d2e573"; // API access key for http://api.weatherstack.com/

    public WeatherForecastClient() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public WeatherResponse getWeather(String cityName) {
        // todo: http://api.weatherstack.com/current?access_key=814e951ff41c347812903ae501d2e573&query=london

        //http://api.weatherstack.com/current?access_key=814e951ff41c347812903ae501d2e573&query=london&forecast_day=1
        // todo: create WeatherResponse class

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("http://api.weatherstack.com/current?access_key=%s&query=%s&forecast_day=1", ACCESS_KEY, cityName)))
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();
            System.out.println(responseBody);
            WeatherResponse weatherResponse = objectMapper.readValue(responseBody, WeatherResponse.class);

            return weatherResponse;
        } catch (Exception e) {
            System.out.println("Nie udana próba pobrania pogody" + e.getStackTrace());
            return new WeatherResponse();
            // todo throw BadGatewayException -> 502
        }
    }
}
