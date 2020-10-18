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
    private final String accessKey = "814e951ff41c347812903ae501d2e573"; // API access key for http://api.weatherstack.com/

    public WeatherForecastClient() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public Weather getWeather(String cityName) {
        // todo: http://api.weatherstack.com/current?access_key=814e951ff41c347812903ae501d2e573&query=london
        // todo: create WeatherResponse class

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
            // todo throw BadGatewayException -> 502
        }
    }
}
