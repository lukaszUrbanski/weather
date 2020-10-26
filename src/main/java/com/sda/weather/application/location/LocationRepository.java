package com.sda.weather.application.location;

import com.sda.weather.application.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LocationRepository {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public Location saveNewLocation(final Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(location);

        transaction.commit();
        session.close();

        return location;
    }

    public List<Location> readAllLocations() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Location> locations = session.createQuery("FROM Location")
                .getResultList();

        transaction.commit();
        session.close();

        return locations;
    }

    public boolean checkLocation(final String cityName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        boolean isExist = (Long) session.createQuery("SELECT count(*) FROM Location AS l WHERE l.city = :name")
                .setParameter("name", cityName)
                .uniqueResult() > 0;

        transaction.commit();
        session.close();

        return isExist;
    }

    public Location getLocation(String cityName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Location location = session.createQuery("FROM Location AS l WHERE l.city = :name", Location.class)
                .setParameter("name", cityName)
                .getSingleResult();

        transaction.commit();
        session.close();

        return location;
    }

    public Double[] getCoordinates(final String cityName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Double[] coordinates = new Double[2];
         coordinates[0]  = Double.valueOf(session.createQuery("SELECT max(latitude) FROM Location AS l WHERE l.city = :name")
                .setParameter("name", cityName)
                .getFirstResult());
        coordinates[1]  = Double.valueOf(session.createQuery("SELECT max(longitude) FROM Location AS l WHERE l.city = :name")
                .setParameter("name", cityName)
                .getFirstResult());

        transaction.commit();
        session.close();

        return coordinates;

    }
}
