package org.example.repository;

import org.example.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result,Integer> {
    List<Result> findByUserId(String userId);
    List<Result> findByQuizId(int quizId);
}
