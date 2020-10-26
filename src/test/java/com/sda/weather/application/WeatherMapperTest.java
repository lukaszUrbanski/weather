package com.sda.weather.application;

import com.sda.weather.application.weather.Weather;
import com.sda.weather.application.weather.client.ForecastWeather;
import com.sda.weather.application.weather.client.Temperature;
import com.sda.weather.application.weather.client.WeatherResponse;
import com.sda.weather.application.weather.client.WeatherMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherMapperTest {

    @Test
    void test() { // todo develop me
        // given
        WeatherResponse input = new WeatherResponse();
        ForecastWeather forecastWeather = new ForecastWeather();
        Temperature temperature = new Temperature();
        temperature.setDay(33.0);
        forecastWeather.setTemperature(temperature);
        forecastWeather.setDate("1603540800");
        forecastWeather.setHumidity(21.0);
        forecastWeather.setPressure(1000);
        forecastWeather.setWindDirectory("56");
        forecastWeather.setWindSpeed(5.0);
        input.setDaily(List.of(forecastWeather, forecastWeather));

        // when
        Weather result = WeatherMapper.mapToWeather(input, LocalDate.now());

        // then
        assertEquals(result.getWindSpeed(), 5.0);
    }
}
