package com.sda.weather.application.weather.client;

import com.sda.weather.application.weather.Weather;

import java.time.Instant;
import java.time.LocalDate;
import java.util.TimeZone;

public class WeatherMapper {

    public static Weather mapToWeather(WeatherResponse weatherResponse, final LocalDate weatherDate) {
        int day = weatherDate.getDayOfYear() - LocalDate.now().getDayOfYear();
        ForecastWeather forecastWeather = weatherResponse.getDaily().get(day);

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
