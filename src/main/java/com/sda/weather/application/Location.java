package com.sda.weather.application;

public class Location {

    private String name;
    private String coordinate;
    private String region;
    private String country;

    public Location(final String name, final String coordinate, final String region, final String country) {
        this.name = name;
        this.coordinate = coordinate;
        this.region = region;
        this.country = country;
    }

    @Override
    public String toString() {
        return "{" +
                "'name':'" + name + '\'' +
                ", 'coordinate':'" + coordinate + '\'' +
                ", 'region':'" + region + '\'' +
                ", 'country':'" + country + '\'' +
                '}';
    }
}
