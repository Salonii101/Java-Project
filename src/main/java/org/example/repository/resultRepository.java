package org.example.repository;

import org.example.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface resultRepository {
    Result findById(int id);
    List<Result> findAll();
}
