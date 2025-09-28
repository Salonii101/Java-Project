package org.example.services;

import org.example.models.Quiz;
import org.example.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    // Save or update a quiz
    public Quiz saveOrUpdateQuiz(Quiz quiz) {
        return quizRepository.save(quiz); // handles both save & update
    }

    // Delete a quiz by entity
    public void deleteQuiz(Quiz quiz) {
        quizRepository.delete(quiz);
    }

    // Delete by ID
    public void deleteQuizById(int id) {
        quizRepository.deleteById(id);
    }

    // Find a quiz by ID
    public Quiz findQuizById(int id) {
        return quizRepository.findById(id).orElse(null) ;
    }

    // Get all quizzes
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // Optional: get quizzes by subject
    public List<Quiz> getQuizzesBySubject(int subjectId) {
        return quizRepository.findBySubjectId(subjectId);
    }
}
