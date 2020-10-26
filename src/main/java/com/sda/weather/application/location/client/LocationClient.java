package com.sda.weather.application.location.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.application.BadGatewayException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LocationClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String WEATHERSTACK_ACCESS_KEY = "814e951ff41c347812903ae501d2e573"; // API access key for http://api.weatherstack.com/

    public LocationClient(){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public NewLocation getLocation(String cityName) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("http://api.weatherstack.com/current?access_key=%s&query=%s",WEATHERSTACK_ACCESS_KEY, cityName.replaceAll(" ", "_"))))
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();
            System.out.println(responseBody);
            NewLocation newLocation = objectMapper.readValue(responseBody, NewLocation.class);

            return newLocation;
        } catch (Exception e) {
            throw new BadGatewayException("Nie udana próba pobrania pogody" + e.getStackTrace());
        }
    }
}
