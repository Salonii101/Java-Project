package org.example.dao;

import org.example.Utils.Question;

import java.util.List ;

public interface QuestionDAO {

        void save(Question question);
        void update(Question question);
        void delete(Question question);
        Question findById(int id);
        List<Question> findAll();
        List<Question> findByQuizId(int quizId); // extra helper


}
