package com.sda.weather.application.request_response;

import lombok.Data;

@Data
public class Current {   // ta klasa może być zdefiniowana w sobnym pliku np. Current.java
    private int temperature;
    private int pressure;
    private double humidity;
    private String wind_dir;
    private double wind_speed;
}