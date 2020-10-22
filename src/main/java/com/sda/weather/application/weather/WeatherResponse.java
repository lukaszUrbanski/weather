package com.sda.weather.application.weather;

import lombok.Data;

@Data
public class WeatherResponse {

//    String name;
//    String country;
//    String Region;
//    String lat;
//    String lon;
//    String localtime;
//    String utc_offset;

    private Current current;

    @Data
    public static class Current {
        private int temperature;
        private int pressure;
        private int humidity;
        private String wind_dir;
        private int wind_speed;
    }
}
