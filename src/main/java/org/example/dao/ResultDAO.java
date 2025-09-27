package org.example.dao;

import java.util.List;

import org.example.models.Result;

public interface ResultDAO {
    void save(Result result);
    void update(Result result);
    void delete(Result result);
    Result findById(int id);
    List<Result> findAll();
}
