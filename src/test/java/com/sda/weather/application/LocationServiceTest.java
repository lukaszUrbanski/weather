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

}
