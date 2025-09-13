package org.example.services;

import org.example.dao.QuestionDAO;
import org.example.models.Question;

import java.util.List;

public class QuestionService {

    private final QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    // Save a new question
    public void saveQuestion(Question question) {
        questionDAO.save(question);
    }

    // Update an existing question
    public void updateQuestion(Question question) {
        questionDAO.update(question);
    }

    // Delete a question
    public void deleteQuestion(Question question) {
        questionDAO.delete(question);
    }

    // Find a question by ID
    public Question findQuestionById(int id) {
        return questionDAO.findById(id);
    }

    // Get all questions
    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    // Get questions by subject ID
    public List<Question> getQuestionsBySubject(int subjectId) {
        return questionDAO.getQuestionsBySubject(subjectId);
    }

    // (Optional) Get questions by quiz ID if your schema ever links questions to quizzes
    public List<Question> getQuestionsByQuizId(int quizId) {
        return questionDAO.findByQuizId(quizId);
    }
}
