package org.example.dao.impl ;

import org.example.dao.UserDAO;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    public UserImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }
}
