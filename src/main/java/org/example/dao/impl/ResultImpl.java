package org.example.dao.impl;

import org.example.dao.ResultDAO;
import org.example.models.Result;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class ResultImpl implements ResultDAO {

    private final Object sessionFactory;

    public ResultImpl(Object sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Result result) {
        // stub
    }

    @Override
    public void update(Result result) {
        // stub
    }

    @Override
    public void delete(Result result) {
        // stub
    }

    @Override
    public Result findById(int id) {
        return null;
    }

    @Override
    public List<Result> findAll() {
        return Collections.emptyList();
    }
}
