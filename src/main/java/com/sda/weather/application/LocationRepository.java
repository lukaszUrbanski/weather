package com.sda.weather.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class LocationRepository {

    private final SessionFactory sessionFactory;

    public LocationRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        sessionFactory  = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }


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
}
