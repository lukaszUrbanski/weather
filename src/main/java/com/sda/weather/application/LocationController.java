package com.sda.weather.application;

import java.util.List;

public class LocationController {
    private final LocationService locationService = new LocationService();

    public String addLocation(final String name, final String coordinate, final String region, final String country) {

       Location location =  locationService.addNewLocation(name, coordinate, region, country);

       return location.toString();
    }

    public String showAllLocations() {
        List<Location> locations = locationService.readAllLocations();

        return locations.toString();

    }
}
