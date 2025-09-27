package org.example.dao.impl;

import java.util.List;

import org.example.dao.QuizDAO;
import org.example.models.Quiz;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class QuizImpl implements QuizDAO {

    private final SessionFactory sessionFactory;

    public QuizImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Quiz quiz) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.persist(quiz);

            tx.commit();
            System.out.println("✅ Quiz saved: " + quiz.getId());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void update(Quiz quiz) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.merge(quiz);

            tx.commit();
            System.out.println("✅ Quiz updated: " + quiz.getId());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void delete(Quiz quiz) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.remove(quiz);

            tx.commit();
            System.out.println("✅ Quiz deleted: " + quiz.getId());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Quiz findById(int id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.find(Quiz.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Quiz> findAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM Quiz", Quiz.class).list();
        } finally {
            if (session != null) session.close();
        }
    }
}
