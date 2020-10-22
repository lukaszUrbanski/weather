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
    int temperature;
    int pressure;
    Double humidity;
    String windDirectory;
    Double windSpeed;
    @ManyToOne
    Location location;

    public Weather(final int temperature, final int pressure, final Double humidity, final String windDirectory, final Double windSpeed, final Location location) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirectory = windDirectory;
        this.windSpeed = windSpeed;
        this.location = location;
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
