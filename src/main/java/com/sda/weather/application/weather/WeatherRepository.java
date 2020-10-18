package com.sda.weather.application.weather;

import com.sda.weather.application.HibernateUtils;
import org.hibernate.SessionFactory;

public class WeatherRepository {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public Weather saveWeather(final Weather weather) {
        return weather; // todo develop this part of code
    }
}
