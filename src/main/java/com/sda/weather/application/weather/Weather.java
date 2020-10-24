package com.sda.weather.application.weather;

import com.sda.weather.application.location.Location;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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
    LocalDate date;
    @ManyToOne
    Location location;

    public Weather(Double temperature, int pressure, Double humidity, String windDirectory, Double windSpeed) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirectory = windDirectory;
        this.windSpeed = windSpeed;
    }

    public Weather(final Double temperature, final int pressure, final Double humidity, final String windDirectory, final Double windSpeed, final LocalDate date) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirectory = windDirectory;
        this.windSpeed = windSpeed;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windDirectory='" + windDirectory + '\'' +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
