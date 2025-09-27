package org.example.dao.impl;

import java.util.List;
import java.util.UUID;

import org.example.dao.UserDAO;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    public UserImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
public void save(User user) {
    Transaction tx = null;
    Session session = null;
    try {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        System.out.println("✅ User saved: " + user.getName());
    } catch (Exception e) {
        if (tx != null && tx.isActive()) tx.rollback();
        e.printStackTrace();
    } finally {
        if (session != null && session.isOpen()) session.close();
    }
}

@Override
public void update(User user) {
    Transaction tx = null;
    Session session = null;
    try {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
        System.out.println("✅ User updated: " + user.getName());
    } catch (Exception e) {
        if (tx != null && tx.isActive()) tx.rollback();
        e.printStackTrace();
    } finally {
        if (session != null && session.isOpen()) session.close();
    }
}

@Override
public void delete(User user) {
    Transaction tx = null;
    Session session = null;
    try {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.remove(user);
        tx.commit();
        System.out.println("✅ User deleted: " + user.getName());
    } catch (Exception e) {
        if (tx != null && tx.isActive()) tx.rollback();
        e.printStackTrace();
    } finally {
        if (session != null && session.isOpen()) session.close();
    }
}

@Override
public User findById(String id) {
    Session session = null;
    try {
        session = sessionFactory.openSession();
        return session.find(User.class, UUID.fromString(id));
    } finally {
        if (session != null && session.isOpen()) session.close();
    }
}

@Override
public List<User> findAll() {
    Session session = null;
    try {
        session = sessionFactory.openSession();
        return session.createQuery("from User", User.class).list();
    } finally {
        if (session != null && session.isOpen()) session.close();
    }
}
}