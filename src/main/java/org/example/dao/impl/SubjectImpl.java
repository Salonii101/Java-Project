package org.example.dao.impl;

import java.util.List;

import org.example.dao.SubjectDAO;
import org.example.models.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SubjectImpl implements SubjectDAO {

    private final SessionFactory sessionFactory;

    public SubjectImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Subject subject) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(subject);
            tx.commit();
            System.out.println("✅ Saved subject: " + subject.getName());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Subject subject) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(subject);
            tx.commit();
            System.out.println("✅ Subject updated: " + subject.getName());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Subject subject) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(subject);
            tx.commit();
            System.out.println("✅ Subject deleted: " + subject.getName());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
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
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Subject", Subject.class).list();
        }
    }

    @Override
    public List<Subject> findAll() {
        // Optional: reuse getAllSubjects or implement separately
        return getAllSubjects();
    }
}
