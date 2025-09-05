package org.example.dao;

import org.example.models.Result;
import java.util.List;

public interface ResultDAO {
    void save(Result result);
    void update(Result result);
    void delete(Result result);
    Result findById(int id);
    List<Result> findAll();
}
