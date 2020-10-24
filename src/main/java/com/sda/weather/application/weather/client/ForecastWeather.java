package com.sda.weather.application.weather.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForecastWeather {

    @JsonProperty("dt")
    private String date;
    @JsonProperty("temp")
    private Temperature temperature;
    private int pressure;
    private Double humidity;
    @JsonProperty("wind_deg")
    private String windDirectory;
    @JsonProperty("wind_speed")
    private Double windSpeed;

}
