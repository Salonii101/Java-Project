package org.example.services;

import org.example.dao.QuestionDAO;
import org.example.models.Questions;

import java.util.List;

public class QuestionService {

    private final QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    // Save a new question
    public void saveQuestion(Questions question) {
        questionDAO.save(question);
    }

    // Update an existing question
    public void updateQuestion(Questions question) {
        questionDAO.update(question);
    }

    // Delete a question
    public void deleteQuestion(Questions question) {
        questionDAO.delete(question);
    }

    // Find a question by ID
    public Questions findQuestionById(int id) {
        return questionDAO.findById(id);
    }

    // Get all questions
    public List<Questions> getAllQuestions() {
        return questionDAO.findAll();
    }

    // Get questions by subject ID
//    public List<Questions> getQuestionsBySubject(int subjectId) {
//        return questionDAO.findBySubjectId(subjectId);
//    }
}
