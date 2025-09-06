package org.example.services;

import org.example.dao.ResultDAO;
import org.example.dao.UserDAO;
import org.example.dao.QuizDAO;
import org.example.dao.impl.ResultImpl;
import org.example.dao.impl.UserImpl;
import org.example.dao.impl.QuizImpl;
import org.example.models.Result;
import org.example.models.User;
import org.example.models.Quiz;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

public class ResultService {
    private final ResultDAO resultDAO;
    private final UserDAO userDAO;
    private final QuizDAO quizDAO;

    public ResultService(SessionFactory sessionFactory) {
        this.resultDAO = new ResultImpl(sessionFactory);
        this.userDAO = new UserImpl(sessionFactory);
        this.quizDAO = new QuizImpl(sessionFactory);
    }

    public void saveResult(String userId, String quizId, int score) {
        User user = userDAO.findById(userId);
        Quiz quiz = quizDAO.findById(quizId);

        if (user == null || quiz == null) {
            throw new IllegalArgumentException("Invalid user or quiz id");
        }

        Result result = new Result();
        result.setUser(user);
        result.setQuiz(quiz);
        result.setScore(score);
        result.setTakenAt(LocalDateTime.now());

        resultDAO.save(result);
    }
}
