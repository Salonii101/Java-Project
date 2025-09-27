package org.example.dao.impl;

import java.util.List;

import org.example.dao.ResultDAO;
import org.example.models.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ResultImpl implements ResultDAO {

    private final SessionFactory sessionFactory;

    public ResultImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Result result) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.persist(result);

            tx.commit();
            System.out.println("✅ Saved result: " + result.getId());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void update(Result result) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.merge(result);

            tx.commit();
            System.out.println("✅ Updated result: " + result.getId());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void delete(Result result) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.remove(result);

            tx.commit();
            System.out.println("✅ Deleted result: " + result.getId());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Result findById(int id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.find(Result.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Result> findAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM Result", Result.class).list();
        } finally {
            if (session != null) session.close();
        }
    }
}
