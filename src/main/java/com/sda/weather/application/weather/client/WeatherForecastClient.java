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
    private final String WEATHERSTACK_ACCESS_KEY = "814e951ff41c347812903ae501d2e573"; // API access key for http://api.weatherstack.com/
    private final String OPEN_WEATHER_ACCESS_KEY = "5fa06312f1b8df12879a8b9344f6255d"; // API access key for https://api.openweathermap.org

    public WeatherForecastClient() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public WeatherResponse getWeather(String cityName) {
        // todo: http://api.weatherstack.com/current?access_key=814e951ff41c347812903ae501d2e573&query=london
        // todo: create WeatherResponse class

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(URI.create(String.format("http://api.weatherstack.com/current?access_key=%s&query=%s",WEATHERSTACK_ACCESS_KEY, cityName)))
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();
            System.out.println(responseBody);
            WeatherResponse weatherResponse = objectMapper.readValue(responseBody, WeatherResponse.class);

            return weatherResponse;
        } catch (Exception e) {
            throw new BadGatewayException("Nie udana próba pobrania pogody" + e.getStackTrace());
            // todo throw BadGatewayException -> 502
        }
    }
    public WeatherResponseByCoordinates getWeather(Double lat, Double lon) {


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(URI.create(String.format("https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&units=metric&time=1603627200&appid=%s",lat, lon, OPEN_WEATHER_ACCESS_KEY)))
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();
            System.out.println(responseBody);
            WeatherResponseByCoordinates weatherResponseByCoordinates = objectMapper.readValue(responseBody, WeatherResponseByCoordinates.class);

            return weatherResponseByCoordinates;
        } catch (Exception e) {
            throw new BadGatewayException("Nie udana próba pobrania pogody" + e.getStackTrace());
            // todo throw BadGatewayException -> 502
        }
    }

}
