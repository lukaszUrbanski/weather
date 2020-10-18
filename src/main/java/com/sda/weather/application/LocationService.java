package com.sda.weather.application;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository = new LocationRepository();

    public Location addNewLocation(final String name, final String coordinate, final String region, final String country) {

        if (name == null || name.isEmpty() ){
            throw new RuntimeException("City name can't bo empty.");
        }



        Location location = new Location(name, coordinate,region,country);
        Location savedLocation = locationRepository.saveNewLocation(location);

        return savedLocation;
    }

    public List<Location> readAllLocations() {

        return locationRepository.readAllLocations();
    }
}
