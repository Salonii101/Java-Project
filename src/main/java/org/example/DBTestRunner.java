package org.example;

import java.util.List;

import org.example.dao.impl.QuestionImpl;
import org.example.dao.impl.QuizImpl;
import org.example.dao.impl.ResultImpl;
import org.example.dao.impl.SubjectImpl;
import org.example.dao.impl.UserImpl;
import org.example.models.Question;
import org.example.models.Quiz;
import org.example.models.Result;
import org.example.models.Subject;
import org.example.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBTestRunner {

    public static void main(String[] args) {

        // Bootstrap Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        try {
            // Initialize DAO implementations with SessionFactory
            UserImpl userDAO = new UserImpl(sessionFactory);
            SubjectImpl subjectDAO = new SubjectImpl(sessionFactory);
            QuizImpl quizDAO = new QuizImpl(sessionFactory);
            QuestionImpl questionDAO = new QuestionImpl(sessionFactory);
            ResultImpl resultDAO = new ResultImpl(sessionFactory);

            // ---- Fetch and print Users ----
            System.out.println("----- Users -----");
            List<User> users = userDAO.findAll(); // adjust method if your DAO uses a different name
            for (User u : users) {
                System.out.println(u.getId() + " | " + u.getName() + " | " + u.getHashPassword() + " | " + u.getRole());
            }

            // ---- Fetch and print Subjects ----
            System.out.println("\n----- Subjects -----");
            List<Subject> subjects = subjectDAO.findAll();
            for (Subject s : subjects) {
                System.out.println(s.getId() + " | " + s.getName());
            }

            // ---- Fetch and print Quizzes ----
            System.out.println("\n----- Quizzes -----");
            List<Quiz> quizzes = quizDAO.findAll();
            for (Quiz q : quizzes) {
                System.out.println(q.getId() + " | " + q.getTitle() + " | User: " + q.getUser().getName()
                        + " | Subject: " + q.getSubject().getName());
            }

            // ---- Fetch and print Questions ----
            System.out.println("\n----- Questions -----");
            List<Question> questions = questionDAO.findAll();
            for (Question qn : questions) {
                System.out.println(qn.getId() + " | " + qn.getQuestionText() + " | Correct: " + qn.getCorrectOption()
                        + " | Quiz: " + qn.getQuiz().getTitle() + " | Subject: " + qn.getSubject().getName());
            }

            // ---- Fetch and print Results ----
            System.out.println("\n----- Results -----");
            List<Result> results = resultDAO.findAll();
            for (Result r : results) {
                System.out.println(r.getId() + " | User: " + r.getUser().getName() + " | Quiz: "
                        + r.getQuiz().getTitle() + " | Score: " + r.getScore() + " | TakenAt: " + r.getTakenAt());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }

        System.out.println("\n✅ Data fetched successfully from existing tables!");
    }
}
