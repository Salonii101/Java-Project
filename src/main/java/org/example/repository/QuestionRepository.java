package org.example.repository;

import org.example.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findByQuizId(int quizId) ;
    List<Question> getQuestionsBySubject(int subjectId) ;
    List<Question> findBySubjectId(int subjectId);
}
