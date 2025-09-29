package org.example.repository;

import org.example.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findByQuizId(int quizId) ;
    List<Question> findBySubjectId(int subjectId);
}
