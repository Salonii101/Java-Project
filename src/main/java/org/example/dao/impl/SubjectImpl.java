package org.example.dao.impl;

import org.example.dao.SubjectDAO;
import org.example.models.Subject;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class SubjectImpl implements SubjectDAO {

    private final Object sessionFactory;

    public SubjectImpl(Object sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Subject subject) {
        // stub
    }

    @Override
    public void update(Subject subject) {
        // stub
    }

    @Override
    public void delete(Subject subject) {
        // stub
    }

    @Override
    public Subject findById(String id) {
        return null;
    }

    @Override
    public List<Subject> findAll() {
        return Collections.emptyList();
    }
}
