package org.example.services;

import org.example.models.Result;
import org.example.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    // ===== Create or Update Result =====
    public Result saveUpdateResult(Result result) {
        return resultRepository.save(result);
    }

    // ===== Find Result by UUID =====
    public Result findResultById(UUID id) {
        return resultRepository.findById(id).orElse(null);
    }

    // ===== Get All Results =====
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // ===== Get Results by User =====
    public List<Result> getResultsByUser(String userId) {
        // Assuming User entity's ID is also a UUID
        UUID uuid = UUID.fromString(userId);
        return resultRepository.findByUserId(uuid);
    }

    // ===== Get Results by Quiz =====
    public List<Result> getResultsByQuiz(Integer quizId) {
        return resultRepository.findByQuizId(quizId);
    }

    // ===== Delete a Result =====
    public void deleteResult(Result result) {
        resultRepository.delete(result);
    }
}
