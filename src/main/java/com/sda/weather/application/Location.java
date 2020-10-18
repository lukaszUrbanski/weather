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
    private Double latitude;
    private Double longitude;
    private String region;
    private String country;

    public Location(){

    }

    public Location(final String city, final Double latitude, final Double longitude,  final String region, final String country) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.country = country;
    }
}
