package org.example.services;


import org.example.models.Subject;
import org.example.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository ;

    public Subject saveOrUpdateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Delete a subject by entity
    public void deleteSubject(Subject subject) {
        subjectRepository.delete(subject);
    }

    // Delete by ID
    public void deleteSubjectById(int id) {
        subjectRepository.deleteById(id);
    }

    // Find a subject by ID
    public Subject findSubjectById(int id) {
        return subjectRepository.findById(id).orElse(null) ;
    }

    // Get all subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
