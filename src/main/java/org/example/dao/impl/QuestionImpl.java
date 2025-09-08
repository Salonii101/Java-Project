package org.example.dao.impl;

import org.example.Utils.Question;
import org.example.dao.QuestionDAO;
import org.example.models.Questions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class QuestionImpl implements QuestionDAO {

    private final SessionFactory sessionFactory ;

    public QuestionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Questions question) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(question);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Questions question) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(question);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Questions question) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(question);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Questions findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Questions.class, id);
        }
    }

    @Override
    public List<Questions> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Question", Questions.class).list();
        }
    }

    @Override
    public List<Questions> findByQuizId(int quizId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Question q where q.quiz.id = :quizId", Questions.class)
                    .setParameter("quizId", quizId)
                    .list();
        }
    }

    @Override
    public List<Questions> getQuestionsBySubject(int subjectId) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Question q WHERE q.subjectId = :subjectId", Questions.class)
                    .setParameter("subjectId",subjectId).getResultList() ;
        }
    }
}
