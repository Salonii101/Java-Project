package org.example.services;

import org.example.dao.SubjectDAO;

import org.example.dao.impl.SubjectImpl;
import org.example.models.Subject;

import java.util.List;

public class SubjectService {
    private final SubjectDAO subjectDAO = new SubjectImpl();

    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }
}
