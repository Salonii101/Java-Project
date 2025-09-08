package org.example.dao.impl ;

import org.example.dao.SubjectDAO;
import org.example.models.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SubjectImpl implements SubjectDAO {

    private final SessionFactory sessionFactory;

    public SubjectImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Subject subject) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(subject);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }


    @Override
    public void update(Subject subject) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.merge(subject);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try { tx.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void delete(Subject subject) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.remove(subject);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try { tx.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }


    @Override
    public Subject findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Subject.class, id);
        }
    }

    @Override
    public List<Subject> getAllSubjects() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Subject", Subject.class).getResultList() ;
        }
    }

    @Override
    public List<Subject> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Subject", Subject.class).list();
        }
    }
}
