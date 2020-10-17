package com.sda.weather.application;

public class Location {

    private String name;
    private String coordinate;
    private String region;
    private String country;


    public Location(final String name, final String coordinate, final String region, final String country) {
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
