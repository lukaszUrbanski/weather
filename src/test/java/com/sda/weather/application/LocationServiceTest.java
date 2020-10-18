package com.sda.weather.application;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    private final LocationService locationService = new LocationService();

    @Test
    void saveNewLocation_returnNewLocation() {
        // when
        Location response = locationService.addNewLocation("Torun", "22 45", "Kujawsko-pomorskie", "Polska");

        // then
        assertNotNull(response);
        assertEquals(response.getCountry(), "Polska");
        assertEquals(response.getCity(), "Torun");
    }

    @Test
    void saveNewLocation_whenCityValueIsEmpty_shouldThrowException() {
        // when
        assertThrows(Exception.class, () -> locationService.addNewLocation("", "20 20", "region", "country"));
    }

    @Test
    void saveNewLocation_whenCountryValueIsEmpty_shouldThrowException() {
        // when
        assertThrows(Exception.class, () -> locationService.addNewLocation("city", "20 20", "region", ""));
    }

    @Test
    void saveNewLocation_whenCoordinatesAreWrong_shouldThrowException() {
        // when
        assertThrows(Exception.class, () -> locationService.addNewLocation("city", "200 -200", "region", ""));
    }
}
