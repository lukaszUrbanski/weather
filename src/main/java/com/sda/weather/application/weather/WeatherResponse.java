package com.sda.weather.application.weather;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherResponse {
//
//    String name;
//    String country;
//    String Region;
//    String lat;
//    String lon;
//    String localtime;
//    String utc_offset;

    int temperature;
    int pressure;
    int humidity;
    String wind_dir;
    int wind_speed;

}
