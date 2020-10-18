package com.sda.weather.application.weather;

import com.sda.weather.application.location.Location;
import lombok.Data;

import javax.persistence.*;

@Data
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
}