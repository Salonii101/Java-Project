package org.example.dao.impl;

import org.example.dao.QuizDAO;
import org.example.models.Quiz;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class QuizImpl implements QuizDAO {

    private final Object sessionFactory;

    public QuizImpl(Object sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Quiz quiz) {
        // stub implementation for compilation
    }

    @Override
    public void update(Quiz quiz) {
        // stub implementation for compilation
    }

    @Override
    public void delete(Quiz quiz) {
        // stub implementation for compilation
    }

    @Override
    public Quiz findById(String id) {
        return null;
    }

    @Override
    public List<Quiz> findAll() {
        return Collections.emptyList();
    }
}
