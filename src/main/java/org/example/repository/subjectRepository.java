package org.example.repository;

import org.example.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface subjectRepository extends JpaRepository{
    Subject findById(int id) ;
    List<Subject> getAllSubjects();
    List<Subject> findAll();
}
