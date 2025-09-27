package org.example;

import java.util.List;
import java.util.Scanner;

import org.example.dao.impl.QuizImpl;
import org.example.models.Question;
import org.example.models.Quiz;
import org.example.models.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QuizTestApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hibernate bootstrap
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        QuizImpl quizDAO = new QuizImpl(sessionFactory);

        while (true) {
            System.out.println("\n--- Quiz Test Menu ---");
            System.out.println("1. Save Quiz");
            System.out.println("2. Update Quiz Title");
            System.out.println("3. Delete Quiz");
            System.out.println("4. Fetch Quiz by ID");
            System.out.println("5. Fetch All Quizzes");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 6) break;

            try {
                switch (choice) {
                    case 4 -> { // Fetch Quiz by ID
                        System.out.print("Enter Quiz ID to fetch: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        // Open session to avoid LazyInitializationException
                        try (Session session = sessionFactory.openSession()) {
                            Quiz quiz = session.find(Quiz.class, id);
                            if (quiz != null) {
                                // Access subject and questions inside session
                                Subject subject = quiz.getSubject();
                                System.out.println("ID: " + quiz.getId());
                                System.out.println("Title: " + quiz.getTitle());
                                System.out.println("Subject: " + subject.getName());

                                List<Question> questions = quiz.getQuestions();
                                System.out.println("Questions for this quiz:");
                                for (Question q : questions) {
                                    System.out.println(" - " + q.getQuestionText());
                                }
                            } else {
                                System.out.println("Quiz not found!");
                            }
                        }
                    }

                    case 5 -> { // Fetch all quizzes
                        try (Session session = sessionFactory.openSession()) {
                            List<Quiz> quizzes = session.createQuery("FROM Quiz", Quiz.class).list();
                            for (Quiz quiz : quizzes) {
                                System.out.println("\nID: " + quiz.getId());
                                System.out.println("Title: " + quiz.getTitle());
                                System.out.println("Subject: " + quiz.getSubject().getName());
                                System.out.println("Number of Questions: " + quiz.getQuestions().size());
                            }
                        }
                    }

                    default -> System.out.println("Option not implemented in this test.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scanner.close();
        sessionFactory.close();
    }
}
