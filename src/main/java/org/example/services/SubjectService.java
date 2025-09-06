package org.example.services;

import org.example.dao.SubjectDAO;
import org.example.dao.impl.SubjectImpl;
import org.example.models.Subject;
import org.hibernate.SessionFactory;

import java.util.List;

public class SubjectService {
    private final SubjectDAO subjectDAO;

    public SubjectService(SessionFactory sessionFactory) {
        this.subjectDAO = new SubjectImpl(sessionFactory);
    }

    public List<Subject> getAllSubjects() {
        return subjectDAO.findAll();
    }
}
