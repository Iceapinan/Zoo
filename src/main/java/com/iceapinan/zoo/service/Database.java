package com.iceapinan.zoo.service;
import com.iceapinan.zoo.model.Cage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Database {

    protected SessionFactory sessionFactory = null;

    public Database() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(com.iceapinan.zoo.model.Cage.class);
        cfg.addAnnotatedClass(com.iceapinan.zoo.model.Animal.class);
        sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();

    }

    // CRUD Operations

    public void createCage(Cage cage) {
        updateCage(cage);
    }

    public void updateCage(Cage cage) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cage);
        session.getTransaction().commit();
        session.close();
    }

    public void editCage(Cage cage) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(cage);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteCage(Cage cage) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(cage) ;
        session.getTransaction().commit();
        session.close();
    }

    public Cage getCage(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Cage cage = session.find(Cage.class,  id);
        session.getTransaction().commit();
        session.close();
        return cage;
    }

    public List<Cage> listAllCages() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Cage> cages = (List<Cage>) session.createQuery( "from Cage" ).list();
        session.getTransaction().commit();
        session.close();
        return cages;
    }

    public List<Cage> deleteAllCages() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Cage> cages = (List<Cage>) session.createQuery( "from Cage" ).list();
        for (Cage cage: cages) {
            session.delete(cage) ;
        }
        session.getTransaction().commit();
        session.close();
        return cages;
    }



}
