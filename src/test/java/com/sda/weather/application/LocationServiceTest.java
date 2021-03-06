package com.sda.weather.application;


import com.sda.weather.application.location.Location;
import com.sda.weather.application.location.LocationService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    private final LocationService locationService = new LocationService();

    @Test
    void saveNewLocation_returnNewLocation() {
        // when
        Location response = locationService.addNewLocation("Torun", 1.0, 1.0, "Kujawsko-pomorskie", "Polska");

        // then
        assertNotNull(response);
        assertEquals(response.getCountry(), "Polska");
        assertEquals(response.getCity(), "Torun");
    }

    @Test
    void saveNewLocation_whenCityValueIsEmpty_shouldThrowException() {
        // when
        assertThrows(Exception.class, () -> locationService.addNewLocation(" ", 1.0,1.0, "region", "country"));
    }

    @Test
    void saveNewLocation_whenCountryValueIsEmpty_shouldThrowException() {
        // when
        assertThrows(Exception.class, () -> locationService.addNewLocation("city", 1.0,1.0, "region", ""));
    }

    @Test
    void saveNewLocation_whenCoordinatesAreWrong_shouldThrowException() {
        // when
        assertThrows(Exception.class, () -> locationService.addNewLocation("city", 200.0, 200.0, "region", ""));
    }

    @Test
    void isLocationExist_whenLocationDoesNotExist_shouldReturnFalse(){
        // when
        boolean result = locationService.isLocationExist(UUID.randomUUID().toString());

        // then
        assertFalse(result);
    }
}
