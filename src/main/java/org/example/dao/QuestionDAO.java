package org.example.dao;

import org.example.models.Questions;

import java.util.List ;

public interface QuestionDAO {

        void save(Questions question);
        void update(Questions question);
        void delete(Questions question);
        Questions findById(int id);
        List<Questions> findAll();
        List<Questions> findByQuizId(int quizId);
        List<Questions> getQuestionsBySubject(int subjectId);
}
