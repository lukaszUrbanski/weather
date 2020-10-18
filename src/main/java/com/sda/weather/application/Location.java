package com.sda.weather.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
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
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    Set<Weather> weatherSet = new HashSet<>();

    public Location(final String city, final Double latitude, final Double longitude, final String region, final String country) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.country = country;
    }

    public void saveWeather(final Weather weather) {
        this.weatherSet.add(weather);
        weather.setLocation(this);
    }
}
