package com.sda.weather.application.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.application.InternalServerException;

import java.util.List;

public class LocationController {

    private final LocationService locationService = new LocationService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String addLocation(final String cityName, final Double latitude, final Double longitude, final String region, final String country) {
        Location location = locationService.addNewLocation(cityName, latitude, longitude, region, country);

        return getString(location);
    }

    public String showAllLocations() {
        List<Location> locations = locationService.readAllLocations();

        return getString(locations);
    }

    public <T> String getString(T object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("Data can not be serialised.");
        }
    }
}
