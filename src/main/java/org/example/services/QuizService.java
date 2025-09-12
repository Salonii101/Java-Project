package org.example.services;

import org.example.dao.QuizDAO;
import org.example.models.Quiz;
import org.example.models.User;

import java.util.List;

public class QuizService {

    private final QuizDAO quizDAO;

    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    // Save a new quiz
    public void saveQuiz(Quiz quiz) {
        quizDAO.save(quiz);
    }

    // Update an existing quiz
    public void updateQuiz(Quiz quiz) {
        quizDAO.update(quiz);
    }

    // Delete a quiz
    public void deleteQuiz(Quiz quiz) {
        quizDAO.delete(quiz);
    }

    // Find a quiz by ID
    public Quiz findQuizById(int id) {
        return quizDAO.findById(id);
    }

    // Get all quizzes
    public List<Quiz> getAllQuizzes() {
        return quizDAO.findAll();
    }

//    public List<Quiz> getQuizzesBySubject(int subjectId) {
//            return quizDAO.findBySubjectId(subjectId);
//    }

}
