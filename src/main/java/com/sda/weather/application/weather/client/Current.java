package com.sda.weather.application.weather.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Current {   // ta klasa może być zdefiniowana w sobnym pliku np. Current.java
    private int temperature;
    private int pressure;
    private double humidity;
    @JsonProperty("wind_dir")
    private String windDir;
    @JsonProperty("wind_speed")
    private double windSpeed;
}
