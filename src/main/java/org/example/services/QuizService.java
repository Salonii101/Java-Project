package org.example.services;

import org.example.Utils.Question;
import org.example.dao.QuestionDAO;
import org.example.dao.QuizDAO;
import org.example.dao.QuizImpl;
import org.example.dao.impl.QuestionImpl;
import org.example.models.Questions;
import org.example.models.Quiz;

import java.util.List;

public class QuizService {
    private final QuestionDAO questionDAO = new QuestionImpl();
    private final QuizDAO quizDAO = new QuizImpl();

    public Quiz startQuiz(int subjectId, String title) {
        Quiz quiz = new Quiz();
        quiz.setSubjectId(subjectId);
        quiz.setTitle(title);
        quizDAO.save(quiz);
        return quiz;
    }

    public List<Question> getQuestionsForSubject(int subjectId) {
        return questionDAO.getQuestionsBySubject(subjectId);
    }
}
