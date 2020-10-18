package com.sda.weather.application;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository = new LocationRepository();

    public Location addNewLocation(final String name, final String coordinate, final String region, final String country) {
        Location location = new Location(name, coordinate,region,country);
        Location savedLocation = locationRepository.saveNewLocation(location);

        return savedLocation;
    }

    public List<Location> readAllLocations() {
        return locationRepository.readAllLocations();
    }
}
