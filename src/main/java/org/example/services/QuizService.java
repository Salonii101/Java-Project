package org.example.services;

import org.example.dao.QuestionDAO;
import org.example.dao.QuizDAO;
import org.example.dao.SubjectDAO;
import org.example.dao.impl.QuestionImpl;
import org.example.dao.impl.QuizImpl;
import org.example.dao.impl.SubjectImpl;
import org.example.models.Questions;
import org.example.models.Quiz;
import org.example.models.Subject;
import org.hibernate.SessionFactory;

import java.util.List;

public class QuizService {
    private final QuestionDAO questionDAO;
    private final QuizDAO quizDAO;
    private final SubjectDAO subjectDAO;  // Add this

    public QuizService(SessionFactory sessionFactory) {
        this.questionDAO = new QuestionImpl(sessionFactory);
        this.quizDAO = new QuizImpl(sessionFactory);
        this.subjectDAO = new SubjectImpl(sessionFactory); // Initialize it
    }

    public Quiz startQuiz(String subjectId, String title) {
        Subject subject = subjectDAO.findById(subjectId); // ✅ Use instance and correct method
        if (subject == null) {
            throw new IllegalArgumentException("Invalid subject id: " + subjectId);
        }

        Quiz quiz = new Quiz();
        quiz.setSubject(subject);
        quiz.setTitle(title);
        quizDAO.save(quiz);
        return quiz;
    }

    public List<Questions> getQuestionsForSubject(int subjectId) {
        return questionDAO.getQuestionsBySubject(subjectId);
    }
}
