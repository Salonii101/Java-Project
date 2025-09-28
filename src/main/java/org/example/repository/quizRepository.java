package org.example.repository;

import org.example.models.Quiz;

import org.example.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface quizRepository extends JpaRepository {
    Quiz findById(int id);
    List<Quiz> findAll() ;
}
