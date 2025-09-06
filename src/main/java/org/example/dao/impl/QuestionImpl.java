package org.example.dao.impl;

import org.example.dao.QuestionDAO;
import org.example.models.Questions;
import org.example.models.User;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Collections;

@SuppressWarnings("unused")
public class QuestionImpl implements QuestionDAO {

    private final SessionFactory sessionFactory;

    public QuestionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Questions question) {
        // Minimal no-op implementation to avoid compile-time dependency
        // issues in this environment. The real implementation should use
        // a Session and Transaction to persist the entity.
    }

    @Override
    public void update(Questions question) {
        // No-op stub
    }

    @Override
    public void delete(Questions question) {
        // No-op stub
    }

    @Override
    public Questions findById(int id) {
        return null;
    }

    @Override
    public List<Questions> findAll() {
    return Collections.emptyList();
    }

    @Override
    public List<Questions> findByQuizId(int quizId) {
    return Collections.emptyList();
    }

    @Override
    public List<Questions> getQuestionsBySubject(int subjectId) {
    return Collections.emptyList();
    }
}
