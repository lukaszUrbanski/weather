package com.sda.weather.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class LocationController {

    private final LocationService locationService = new LocationService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String addLocation(final String name, final String coordinate, final String region, final String country) {
       Location location =  locationService.addNewLocation(name, coordinate, region, country);

        try {
            return objectMapper.writeValueAsString(location);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("...");
        }
    }

    public String showAllLocations() {
        List<Location> locations = locationService.readAllLocations();

        try {
            return objectMapper.writeValueAsString(locations);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("...");
        }
    }
}
