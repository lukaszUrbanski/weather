package com.sda.weather.application;

public class LocationController {
    private final LocationService locationService = new LocationService();

    public void addLocation(final String name, final String coordinate, final String region, final String country) {

        locationService.addNewLocation(name, coordinate, region, country);
    }
}
