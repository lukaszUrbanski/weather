package com.sda.weather.application;

public class CoordinatesValidator {
   public static boolean isCoordinatesCorrect(final Double latitude, final Double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new WrongDataException("Latitude is wrong.");
        }

        if (longitude < -180 || longitude > 180) {
            throw new WrongDataException("Longitude is wrong.");
        }
        return true;
    }
}
