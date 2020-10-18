package com.sda.weather.application;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String coordinate;
    private String region;
    private String country;


    public Location(final String city, final String coordinate, final String region, final String country) {
        this.city = city;
        this.coordinate = coordinate;
        this.region = region;
        this.country = country;
    }

    @Override
    public String toString() {
        return "{" +
                "'name':'" + city + '\'' +
                ", 'coordinate':'" + coordinate + '\'' +
                ", 'region':'" + region + '\'' +
                ", 'country':'" + country + '\'' +
                '}';
    }
}
