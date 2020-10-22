package com.sda.weather.application.weather;

import lombok.Data;

@Data
public class WeatherResponse {  // w resources/example_response.json wkleiłem strukturę otrzymanego JSON'a - są tam zagnieżdżone obiekty

//    String name;
//    String country;
//    String Region;
//    String lat;
//    String lon;
//    String localtime;
//    String utc_offset;

    private Current current;

    @Data
    public static class Current {   // ta klasa może być zdefiniowana w sobnym pliku np. Current.java
        private int temperature;
        private int pressure;
        private int humidity;
        private String wind_dir;
        private int wind_speed;
    }
}
