package com.sda.weather.application.weather.client;

import com.sda.weather.application.weather.Weather;

public class WeatherMapper {

    public static Weather mapToWeather(WeatherResponse weatherResponse) {
        Current current = weatherResponse.getCurrent();
        return new Weather(
                current.getTemperature(),
                current.getPressure(),
                current.getHumidity(),
                current.getWindDir(),
                current.getWindSpeed()
        );
    }
}
