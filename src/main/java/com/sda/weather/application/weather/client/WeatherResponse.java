package com.sda.weather.application.weather.client;

import lombok.Data;
import java.util.LinkedList;
import java.util.List;

@Data
public class WeatherResponse {

    private List<ForecastWeather> daily = new LinkedList<>();
}
