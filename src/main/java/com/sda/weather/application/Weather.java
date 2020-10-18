package com.sda.weather.application;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double temperature;
    int pressure;
    Double humidity;
    String windDirectory;
    Double windSpeed;
    @ManyToOne
    Location location;


    public void setLocation(final Location location) {
        this.location = location;
    }
}
