package org.example.dao;

import org.example.models.Quiz;
import java.util.List;

public interface QuizDAO {
    void save(Quiz quiz);
    void update(Quiz quiz);
    void delete(Quiz quiz);
    Quiz findById(String id); // ✅ changed to String
    List<Quiz> findAll();
}
