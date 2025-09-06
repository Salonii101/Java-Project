package org.example.services;

import org.example.dao.QuestionDAO;
import org.example.dao.impl.QuestionImpl;
import org.example.models.Questions;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

public class QuestionService {
    private final QuestionDAO questionDAO;

    public QuestionService(SessionFactory sessionFactory) {
        this.questionDAO = new QuestionImpl(sessionFactory);
    }

    public void addQuestion(Questions question) {
        questionDAO.save(question);
    }

    public List<Questions> getQuestionsForSubject(int subjectId) {
        List<Questions> questions = questionDAO.getQuestionsBySubject(subjectId);
        Collections.shuffle(questions);
        return questions;
    }
}
