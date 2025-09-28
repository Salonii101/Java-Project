package org.example.services;

import org.example.repository.ResultRepository ;
import org.example.models.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository ;

    public Result saveUpdateResult(Result result) {
        return resultRepository.save(result) ;
    }


    // Delete a result
    public void deleteResult(Result result) {
        resultRepository.delete(result);
    }

    // Find a result by ID
    public Result findResultById(int id) {
        return resultRepository.findById(id).orElse(null);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll() ;
    }

    public List<Result> getResultsByUser(String userId) {
        return resultRepository.findByUserId(userId);
    }

    // Get results by quiz ID
    public List<Result> getResultsByQuiz(int quizId) {
        return resultRepository.findByQuizId(quizId);
    }
}
