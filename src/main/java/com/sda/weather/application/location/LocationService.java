package com.sda.weather.application.location;

import com.sda.weather.application.CoordinatesValidator;
import com.sda.weather.application.WrongDataException;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository = new LocationRepository();

    public Location addNewLocation(final String cityName, final Double latitude, final Double longitude, final String region, final String country) {
        validateLocation(cityName, latitude, longitude, country);
        Location location = new Location(cityName, latitude, longitude, region, country);

        return locationRepository.saveNewLocation(location);
    }

    public List<Location> readAllLocations() {
        return locationRepository.readAllLocations();
    }

    private void validateLocation(final String cityName, final Double latitude, final Double longitude, final String country) {
        if (cityName == null || cityName.isEmpty()) {
            throw new WrongDataException("City name can't bo empty.");
        }
        if (country == null || country.isEmpty()) {
            throw new WrongDataException("Country name can't bo empty.");
        }
        CoordinatesValidator.isCoordinatesCorrect(latitude, longitude);
    }

    public boolean isLocationExist(final String cityName) {
        return locationRepository.checkLocation(cityName);
    }
}
