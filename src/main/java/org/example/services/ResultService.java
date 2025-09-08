//package org.example.services;
//
//import org.example.dao.ResultDAO;
//import org.example.dao.impl.ResultImpl;
//import org.example.models.Result;
//
//import java.time.LocalDateTime;
//
//public class ResultService {
//    private final ResultDAO resultDAO = new ResultImpl();
//
//    public void saveResult(int userId, int quizId, int score) {
//        Result result = new Result();
//        result.setUserId(userId);
//        result.setQuizId(quizId);
//        result.setScore(score);
//        result.setTakenAt(LocalDateTime.now());
//
//        resultDAO.saveResult(result);
//    }
//}
