package org.example.dao;

import java.util.List;

import org.example.models.Subject;

public interface SubjectDAO {

    void save(Subject subject);
    void update(Subject subject);
    void delete(Subject subject);
    Subject findById(int id);

    // Make sure this matches exactly
    List<Subject> getAllSubjects();

    List<Subject> findAll(); // optional
}
