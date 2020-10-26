package com.sda.weather.application.location.client;

import com.sda.weather.application.location.Location;

public class LocationMapper {

    public static Location mapToLocation (NewLocation newLocation){

        LocationResponse locationResponse = newLocation.getLocationResponse();
        return new Location(
              locationResponse.getCity(),
              locationResponse.getLatitude(),
              locationResponse.getLongitude(),
              locationResponse.getRegion(),
              locationResponse.getCountry()
        );
    }

}
