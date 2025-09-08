package org.example;

import org.example.dao.UserDAO;
import org.example.dao.SubjectDAO;
import org.example.dao.impl.UserImpl;
import org.example.dao.impl.SubjectImpl;
import org.example.models.User;
import org.example.models.Subject;
import org.example.services.UserService;
import org.example.services.SubjectService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Step 1 – Create Hibernate SessionFactory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Make sure this file is in your resources folder
                .buildSessionFactory();

        try {
            // Step 2 – Create DAO implementations
            UserDAO userDAO = new UserImpl(sessionFactory);
            SubjectDAO subjectDAO = new SubjectImpl(sessionFactory);

            // Step 3 – Create service instances
            UserService userService = new UserService(userDAO);
            SubjectService subjectService = new SubjectService(subjectDAO);

            // === User Operations ===

            // Register a new user
            String userName = "JohnDoe";
            String password = "password123";
            String role = "student";
            userService.register(userName, password, role);
            System.out.println("User registered: " + userName);

            // Fetch and print all users
            List<User> users = userService.getAllUsers();
            System.out.println("All Users:");
            for (User user : users) {
                System.out.println(" - " + user.getName() + " (" + user.getRole() + ")");
            }

            // === Subject Operations ===

            // Add a new subject
            String subjectName = "Mathematics";
            subjectService.saveSubject(subjectName);
            System.out.println("Subject added: " + subjectName);

            // Fetch and print all subjects
            List<Subject> subjects = subjectService.getAllSubjects();
            System.out.println("All Subjects:");
            for (Subject subject : subjects) {
                System.out.println(" - " + subject.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 4 – Close the session factory
            sessionFactory.close();
        }
    }
}
