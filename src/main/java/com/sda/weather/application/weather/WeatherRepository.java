package com.sda.weather.application.weather;

import com.sda.weather.application.HibernateUtils;
import org.hibernate.SessionFactory;

public class WeatherRepository {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public void checkLocationWeather(final String cityName) {


        System.out.println("wether for: " + cityName + " is good :) ");
    }
}
