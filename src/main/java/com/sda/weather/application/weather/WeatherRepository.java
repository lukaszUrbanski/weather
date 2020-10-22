package com.sda.weather.application.weather;

import com.sda.weather.application.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class WeatherRepository {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public Weather saveWeather(final Weather weather) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(weather);

        transaction.commit();
        session.close();

        return weather;
    }


}
