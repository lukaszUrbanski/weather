package com.sda.weather.application.weather.client;

import com.sda.weather.application.weather.Weather;

import java.time.LocalDate;

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

    public static  Weather mapToWeather(WeatherResponseByCoordinates weatherResponseByCoordinates){
        ForecastWeather forecastWeather = weatherResponseByCoordinates.getDaily().get(1);
        return new Weather(
                forecastWeather.getTemperature().getDay(),
                forecastWeather.getPressure(),
                forecastWeather.getHumidity(),
                forecastWeather.getWindDirectory(),
                forecastWeather.getWindSpeed(),
                LocalDate.parse(forecastWeather.getDate())
        );
    }
}
