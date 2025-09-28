package org.example.repository;

import org.example.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Integer> {
    List<Result> findByUserId(String userId);
    List<Result> findByQuizId(int quizId);
}
