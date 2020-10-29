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
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WeatherMapperTest {

    @Test
    void mapToWeather() {
        // given
        WeatherResponse input = new WeatherResponse();
        ForecastWeather forecastWeather = new ForecastWeather();
        Temperature temperature = new Temperature();
        temperature.setDay(33.0);
        forecastWeather.setTemperature(temperature);
        forecastWeather.setDate("1603971740"); //2020-10-29
        forecastWeather.setHumidity(21.0);
        forecastWeather.setPressure(1000);
        forecastWeather.setWindDirectory("56");
        forecastWeather.setWindSpeed(5.0);
        input.setDaily(List.of(forecastWeather, forecastWeather));

        // when
        Weather result = WeatherMapper.mapToWeather(input, LocalDate.now());

        // then
        assertNotNull(result);
        assertEquals(result.getWindSpeed(), 5.0);
        assertEquals(result.getDate(), LocalDate.of(2020, 10, 29));
        assertEquals(result.getTemperature(),33.0);
        assertEquals(result.getWindDirectory(), "NE(56°)");


    }
}
