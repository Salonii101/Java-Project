package org.example.dao;

import org.example.Utils.Question;
import org.example.models.Questions;

import java.util.List ;

public interface QuestionDAO {

        void save(Questions question);
        void update(Question question);
        void delete(Question question);
        Question findById(int id);
        List<Question> findAll();
        List<Question> findByQuizId(int quizId); // extra helper


    List<Question> getQuestionsBySubject(int subjectId);
}
