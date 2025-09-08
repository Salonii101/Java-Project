package org.example.dao;

import org.example.models.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ResultImpl implements ResultDAO {

    private final SessionFactory sessionFactory;

    public ResultImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Result result) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(result);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void update(Result result) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(result);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void delete(Result result) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(result);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Result findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Result.class ,id);
        }
    }

    @Override
    public List<Result> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Result", Result.class).list();
        }
    }
}
