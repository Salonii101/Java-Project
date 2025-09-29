package org.example.repository;

import org.example.models.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {
    List<Quiz> findBySubjectId(int subjectId);
}
