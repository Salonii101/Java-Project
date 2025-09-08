package org.example.services;

import org.example.dao.ResultDAO;
import org.example.models.Result;

import java.util.List;

public class ResultService {

    private final ResultDAO resultDAO;

    public ResultService(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    // Save a result
    public void saveResult(Result result) {
        resultDAO.save(result);
    }

    // Update an existing result
    public void updateResult(Result result) {
        resultDAO.update(result);
    }

    // Delete a result
    public void deleteResult(Result result) {
        resultDAO.delete(result);
    }

    // Find a result by ID
    public Result findResultById(int id) {
        return resultDAO.findById(id);
    }

    // Get all results
    public List<Result> getAllResults() {
        return resultDAO.findAll();
    }

    // Additional business logic methods can be added here

    // Example: Get results by user ID
    public List<Result> getResultsByUser(String userId) {
        return resultDAO.findAll().stream()
                .filter(result -> result.getUser().getId().equals(userId))
                .toList();
    }

    // Example: Get results by quiz ID
    public List<Result> getResultsByQuiz(int quizId) {
        return resultDAO.findAll().stream()
                .filter(result -> result.getQuiz().getId() == quizId)
                .toList();
    }
}
