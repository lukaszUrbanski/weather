package com.sda.weather.application.weather.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.application.BadGatewayException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherForecastClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String OPEN_WEATHER_ACCESS_KEY = "5fa06312f1b8df12879a8b9344f6255d"; // API access key for https://api.openweathermap.org

    public WeatherForecastClient() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public WeatherResponse getWeather(Double lat, Double lon) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&units=metric&time=1603627200&appid=%s",lat, lon, OPEN_WEATHER_ACCESS_KEY)))
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();
            WeatherResponse weatherResponse = objectMapper.readValue(responseBody, WeatherResponse.class);

            return weatherResponse;
        } catch (Exception e) {
            throw new BadGatewayException("Nie udana próba pobrania pogody" + e.getStackTrace());
        }
    }

}
