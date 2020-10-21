package com.sda.weather.application.weather;


import lombok.Data;
import lombok.NoArgsConstructor;


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

    public WeatherResponse() {
    }

    public WeatherResponse(final int temperature, final int pressure, final int humidity, final String wind_dir, final int wind_speed) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind_dir = wind_dir;
        this.wind_speed = wind_speed;


    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(final int temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(final int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(final int humidity) {
        this.humidity = humidity;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(final String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public int getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(final int wind_speed) {
        this.wind_speed = wind_speed;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", wind_dir='" + wind_dir + '\'' +
                ", wind_speed=" + wind_speed +
                '}';
    }
}
