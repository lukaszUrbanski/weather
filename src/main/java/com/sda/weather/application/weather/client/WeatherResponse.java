package com.sda.weather.application.weather.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {

    private Current current;
    @JsonProperty("location")
    private ResponseLocation responseLocation;

}
