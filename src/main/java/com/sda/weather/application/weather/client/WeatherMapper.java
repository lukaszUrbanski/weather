package com.sda.weather.application.weather.client;

import com.sda.weather.application.weather.Weather;

import java.time.Instant;
import java.time.LocalDate;
import java.util.TimeZone;

public class WeatherMapper {

    public static Weather mapToWeather(WeatherResponse weatherResponse, final LocalDate weatherDate) {
        int day = weatherDate.getDayOfYear() - LocalDate.now().getDayOfYear();
        ForecastWeather forecastWeather = weatherResponse.getDaily().get(day);

        long timestamp = Long.parseLong(forecastWeather.getDate());
        return new Weather(
                forecastWeather.getTemperature().getDay(),
                forecastWeather.getPressure(),
                forecastWeather.getHumidity(),
                convertWindDirection(forecastWeather.getWindDirectory()),
                forecastWeather.getWindSpeed(),
                LocalDate.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone.getDefault().toZoneId())
        );
    }

    public static String convertWindDirection(String windDegree) {
        String cardinalDirectory;
        double degree = Double.parseDouble(windDegree);
        if (degree > 348.75 && degree <= 11.25) cardinalDirectory = "S";
        else if (degree > 11.25 && degree <= 33.75) cardinalDirectory = "NNE";
        else if (degree > 33.75 && degree <= 56.25) cardinalDirectory = "NE";
        else if (degree > 56.25 && degree <= 78.75) cardinalDirectory = "ENE";
        else if (degree > 78.75 && degree <= 101.25) cardinalDirectory = "E";
        else if (degree > 101.25 && degree <= 123.75) cardinalDirectory = "ESE";
        else if (degree > 123.75 && degree <= 146.25) cardinalDirectory = "SE";
        else if (degree > 146.25 && degree <= 168.75) cardinalDirectory = "SSE";
        else if (degree > 168.75 && degree <= 191.25) cardinalDirectory = "S";
        else if (degree > 191.25 && degree <= 213.75) cardinalDirectory = "SSW";
        else if (degree > 213.75 && degree <= 236.25) cardinalDirectory = "SW";
        else if (degree > 236.25 && degree <= 258.75) cardinalDirectory = "WSW";
        else if (degree > 258.75 && degree <= 281.25) cardinalDirectory = "W";
        else if (degree > 281.75 && degree <= 303.75) cardinalDirectory = "WNW";
        else if (degree > 303.75 && degree <= 326.25) cardinalDirectory = "NW";
        else if (degree > 326.25 && degree <= 348.75) cardinalDirectory = "NNW";
        else cardinalDirectory = "Wrong wind degree";
            return cardinalDirectory  + "(" + windDegree + "°)";
    }
  }
