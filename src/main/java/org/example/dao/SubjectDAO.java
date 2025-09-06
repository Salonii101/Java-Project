package org.example.dao;

import org.example.models.Subject;
import java.util.List;

public interface SubjectDAO {
    void save(Subject subject);
    void update(Subject subject);
    void delete(Subject subject);
    Subject findById(String id);
    List<Subject> findAll();
}
