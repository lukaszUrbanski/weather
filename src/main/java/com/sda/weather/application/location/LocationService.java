package com.sda.weather.application.location;

import com.sda.weather.application.WrongDataException;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository = new LocationRepository();

    public Location addNewLocation(final String cityName, final Double latitude, final Double longitude, final String region, final String country) {
        if (cityName == null || cityName.isEmpty()) {
            throw new WrongDataException("City name can't bo empty.");
        }

        if (country == null || country.isEmpty()) {
            throw new WrongDataException("Country name can't bo empty.");
        }

        if (latitude < -90 || latitude > 90) {
            throw new WrongDataException("Latitude is wrong.");
        }

        if (longitude < -180 || longitude > 180) {
            throw new WrongDataException("Longitude is wrong.");
        }

        Location location = new Location(cityName, latitude, longitude, region, country);

        return locationRepository.saveNewLocation(location);
    }

    public List<Location> readAllLocations() {
        return locationRepository.readAllLocations();
    }

    public boolean isLocationExist(final String cityName) {
        return locationRepository.checkLocation(cityName);
    }
}
