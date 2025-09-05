package org.example.dao;

import org.example.models.Subject;

import java.util.List;

public interface SubjectDAO {
    void save(Subject subject) ;
    void delete(Subject subject) ;
    void update(Subject subject) ;
    Subject findById(int id) ;
    List<Subject> findAll() ;

}
