package org.example.dao;

import org.example.models.User;
import java.util.List;

public interface UserDAO {
    void save(User user);
    void update(User user);
    void delete(User user);
    User findById(int id);
    List<User> findAll();


}
