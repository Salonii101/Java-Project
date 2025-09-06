package org.example;

import org.example.models.Quiz;
import org.example.models.User;
import org.example.services.QuizService;
import org.example.services.ResultService;
import org.example.services.SubjectService;
import org.example.services.UserService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create a Hibernate SessionFactory from hibernate.cfg.xml
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Pass sessionFactory into each service
        UserService userService = new UserService(sessionFactory);
        SubjectService subjectService = new SubjectService(sessionFactory);
        QuizService quizService = new QuizService(sessionFactory);
        ResultService resultService = new ResultService(sessionFactory);

        System.out.println("1. Register\n2. Login");
        int choice = sc.nextInt(); 
        sc.nextLine();

        User loggedInUser = null;
        if (choice == 1) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter password: ");
            String pass = sc.nextLine();
            userService.register(name, pass, "student");
            System.out.println("User registered!");
        }

        System.out.print("Enter login name: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String upass = sc.nextLine();
        loggedInUser = userService.login(uname, upass);

        if (loggedInUser == null) {
            System.out.println("Login failed!");
            sessionFactory.close();
            return;
        }

        // Show subjects
        System.out.println("Choose a subject:");
        var subjects = subjectService.getAllSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i).getName());
        }
    int subChoice = sc.nextInt();
    String subjectId = subjects.get(subChoice - 1).getId();

    Quiz quiz = quizService.startQuiz(subjectId, "Maths Quiz");
        int score = 0;

    // pass the selected subject choice index to the existing method signature
    for (var q : quizService.getQuestionsForSubject(subChoice)) {
            System.out.println(q.getQuestionText());
            // Add user input logic to record answers
        }

        resultService.saveResult(loggedInUser.getId(), quiz.getId(), score);
        System.out.println("Your score: " + score);

        // Close resources
        sc.close();
        sessionFactory.close();
    }
}
