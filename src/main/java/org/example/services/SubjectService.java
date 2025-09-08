package org.example.services;

import org.example.dao.SubjectDAO;
import org.example.models.Subject;

import java.util.List;

public class SubjectService {

    private final SubjectDAO subjectDAO;

    public SubjectService(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    /**
     * Save a new subject.
     */
    public void saveSubject(String name) {
        Subject subject = new Subject(name);
        subjectDAO.save(subject);
    }

    /**
     * Update an existing subject.
     */
    public void updateSubject(Subject subject) {
        subjectDAO.update(subject);
    }

    /**
     * Delete a subject.
     */
    public void deleteSubject(Subject subject) {
        subjectDAO.delete(subject);
    }

    /**
     * Find a subject by ID.
     */
    public Subject findSubjectById(int id) {
        return subjectDAO.findById(id);
    }

    /**
     * Get all subjects.
     */
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    /**
     * Find all subjects (alternative method if needed).
     */
    public List<Subject> findAllSubjects() {
        return subjectDAO.findAll();
    }
}
