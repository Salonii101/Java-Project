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
    public void update(Question question) {
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
    public void delete(Question question) {
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
    public Question findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Question.class, id);
        }
    }

    @Override
    public List<Question> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Question", Question.class).list();
        }
    }

    @Override
    public List<Question> findByQuizId(int quizId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Question q where q.quiz.id = :quizId", Question.class)
                    .setParameter("quizId", quizId)
                    .list();
        }
    }

    @Override
    public List<Question> getQuestionsBySubject(int subjectId) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Question q WHERE q.subjectId = :subjectId", Question.class)
                    .setParameter("subjectId",subjectId).getResultList() ;
        }
    }
}
