package com.sda.weather.application.weather.client;

import com.sda.weather.application.BadGatewayException;
import com.sda.weather.application.location.client.LocationClient;
import com.sda.weather.application.location.client.LocationResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationClientTest {

    private final LocationClient locationClient = new LocationClient();

    @Test
    void getNewLocationWhenCityNameIsCorrect(){
        String cityName = "London";

        LocationResponse locationResponse = locationClient.getLocation(cityName).getLocationResponse();

        assertNotNull(locationResponse);
        assertEquals(locationResponse.getCity(), "London");
        assertEquals(locationResponse.getCountry(), "United Kingdom");
    }


    @Test
    void getNewLocationWhenCityNameIsLowercase(){
        String cityName = "london";

        LocationResponse locationResponse = locationClient.getLocation(cityName).getLocationResponse();

        assertNotNull(locationResponse);
        assertEquals(locationResponse.getCity(), "London");
        assertEquals(locationResponse.getCountry(), "United Kingdom");
    }

    @Test
    void getNewLocationWhenCityNameHasTwoOrMoreWords(){
        String cityName = "New York";

        LocationResponse locationResponse = locationClient.getLocation(cityName).getLocationResponse();

        assertNotNull(locationResponse);
        assertEquals(locationResponse.getCity(), "New York");
        assertEquals(locationResponse.getCountry(), "United States of America");
    }

    @Test
    void getNewLocationWhenCityNameHasPolishLetter(){
        String cityName = "Toruń";

        LocationResponse locationResponse = locationClient.getLocation(cityName).getLocationResponse();

        assertNotNull(locationResponse);
        assertEquals(locationResponse.getCity(), "Toruń");
        assertEquals(locationResponse.getCountry(), "Pologne");
    }

    @Test
    void getNewLocationWhenCityNameIsWrong_locationResponseShouldBeNull(){
        String cityName = "asdasads";

        LocationResponse locationResponse = locationClient.getLocation(cityName).getLocationResponse();

        assertNull(locationResponse);
    }


}
