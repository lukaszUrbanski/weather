package com.sda.weather.application.weather.client;

import com.sda.weather.application.weather.Weather;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

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

    public static Weather mapToWeather(WeatherCoordinatesResponse weatherCoordinatesResponse) {
        ForecastWeather forecastWeather = weatherCoordinatesResponse.getDaily().get(1);
        Long timestamp = Long.valueOf(forecastWeather.getDate());
        return new Weather(
                forecastWeather.getTemperature().getDay(),
                forecastWeather.getPressure(),
                forecastWeather.getHumidity(),
                forecastWeather.getWindDirectory(),
                forecastWeather.getWindSpeed(),
                LocalDate.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone.getDefault().toZoneId())
        );
    }
}
