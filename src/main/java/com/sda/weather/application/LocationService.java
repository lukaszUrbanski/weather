package com.sda.weather.application;

public class LocationService {
    public void addNewLocation(final String name, final String coordinate, final String region, final String country) {

        Location location = new Location(name, coordinate,region,country);
    }
}
