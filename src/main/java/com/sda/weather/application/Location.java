package com.sda.weather.application;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weather")
    Set<Weather> weatherSet = new HashSet<>();

    public Location(){

    }

    public Location(final String city, final Double latitude, final Double longitude,  final String region, final String country) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.country = country;
    }

    public void saveWeather (final Weather weather){
        this.weatherSet.add(weather);
        weather.setLocation(this);
    }
}
