package com.iceapinan.zoo.service;

import com.iceapinan.zoo.model.Cage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.function.Consumer;

public class Database {

    protected SessionFactory sessionFactory;

    public Database() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Cage.class);
        cfg.addAnnotatedClass(com.iceapinan.zoo.model.Animal.class);
        sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
    }

    private void executeWithSession(Consumer<Session> consumer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            consumer.accept(session);
            session.getTransaction().commit();
        }
    }

    // CRUD Operations
    public void createCage(Cage cage) {
        updateCage(cage);
    }

    public void updateCage(Cage cage) {
        executeWithSession(session -> session.save(cage));
    }

    public void editCage(Cage cage) {
        executeWithSession(session -> session.update(cage));
    }

    public void deleteCage(Cage cage) {
        executeWithSession(session -> session.delete(cage));
    }

    public Cage getCage(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Cage cage = session.find(Cage.class, id);
            session.getTransaction().commit();
            return cage;
        }
    }

    public List<Cage> listAllCages() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Cage> cages = session.createQuery("from Cage", Cage.class).list();
            session.getTransaction().commit();
            return cages;
        }
    }

    public List<Cage> deleteAllCages() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Cage> cages = session.createQuery("from Cage", Cage.class).list();
            for (Cage cage : cages) {
                session.delete(cage);
            }
            session.getTransaction().commit();
            return cages;
        }
    }
}
