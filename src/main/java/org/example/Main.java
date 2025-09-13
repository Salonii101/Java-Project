package org.example;

import org.example.dao.UserDAO;
import org.example.dao.SubjectDAO;
import org.example.dao.impl.UserImpl;
import org.example.dao.impl.SubjectImpl;
import org.example.models.*;
import org.example.services.UserService;
import org.example.services.SubjectService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Step 1 – Configure Hibernate and add all annotated classes
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Subject.class);
        cfg.addAnnotatedClass(Quiz.class);
        cfg.addAnnotatedClass(Question.class);
        cfg.addAnnotatedClass(Result.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();

        try {
            // Step 2 – Create DAO implementations
            UserDAO userDAO = new UserImpl(sessionFactory);
            SubjectDAO subjectDAO = new SubjectImpl(sessionFactory);

            // Step 3 – Create service instances
            UserService userService = new UserService(userDAO);
            SubjectService subjectService = new SubjectService(subjectDAO);


            // === User Operations ===
            String userName = "Alice";
            String password = "securePass123";
            String role = "admin";
            userService.register(userName, password, role);
            System.out.println("User registered: " + userName);

            List<User> users = userService.getAllUsers();
            System.out.println("All Users:");
            for (User user : users) {
                System.out.println(" - " + user.getName() + " (" + user.getRole() + ")");
            }

            // === Subject Operations ===
            String subjectName = "Physics";
            subjectService.saveSubject(subjectName);
            System.out.println("Subject added: " + subjectName);

            List<Subject> subjects = subjectService.getAllSubjects();
            System.out.println("All Subjects:");
            for (Subject subject : subjects) {
                System.out.println(" - " + subject.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
