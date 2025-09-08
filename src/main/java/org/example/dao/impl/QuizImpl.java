package org.example.dao.impl;

import org.example.dao.QuizDAO;
import org.example.models.Quiz;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class QuizImpl implements QuizDAO {

    private final SessionFactory sessionFactory;

    public QuizImpl(SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Quiz quiz) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(quiz);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void update(Quiz quiz) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(quiz);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void delete(Quiz quiz) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(quiz);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Quiz findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Quiz.class, id);
        }
    }

    @Override
    public List<Quiz> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Quiz", Quiz.class).list();
        }
    }
}
