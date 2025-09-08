//package org.example.services;
//
//import org.example.Utils.Question;
//import org.example.dao.QuestionDAO;
//import org.example.dao.impl.QuestionImpl;
//import org.example.models.Questions;
//
//import java.util.Collections;
//import java.util.List;
//
//public class QuestionService {
//    private final QuestionDAO questionDAO = new QuestionImpl();
//
//    public void addQuestion(Questions question) {
//        questionDAO.save(question);
//    }
//
//    public List<Question> getQuestionsForSubject(int subjectId) {
//        List<Question> questions = questionDAO.getQuestionsBySubject(subjectId);
//        Collections.shuffle(questions); // Randomize for quiz
//        return questions;
//    }
//}
