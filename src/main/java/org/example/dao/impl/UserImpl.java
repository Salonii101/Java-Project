package org.example.dao.impl;

import org.example.dao.UserDAO;
import org.example.models.User;

import java.util.Collections;
import java.util.List;

public class UserImpl implements UserDAO {

    private final Object sessionFactory;

    public UserImpl(Object sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        // stub
    }

    @Override
    public void update(User user) {
        // stub
    }

    @Override
    public void delete(User user) {
        // stub
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return Collections.emptyList();
    }
}
