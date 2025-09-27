package org.example;

import java.util.List;
import java.util.Scanner;

import org.example.dao.impl.QuestionImpl;
import org.example.models.Question;
import org.example.models.Quiz;
import org.example.models.Subject;
import org.example.services.QuestionService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestQuestion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hibernate session factory
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(Quiz.class)
                .addAnnotatedClass(Subject.class);
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Service
        QuestionService questionService = new QuestionService(new QuestionImpl(sessionFactory));

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Question Test Menu ---");
            System.out.println("1. Save Question");
            System.out.println("2. Update Question Text");
            System.out.println("3. Delete Question");
            System.out.println("4. Fetch Question by ID");
            System.out.println("5. Fetch All Questions");
            System.out.println("6. Fetch Questions by Quiz ID");
            System.out.println("7. Fetch Questions by Subject ID");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> saveQuestion(scanner, questionService);
                case 2 -> updateQuestion(scanner, questionService);
                case 3 -> deleteQuestion(scanner, questionService);
                case 4 -> fetchById(scanner, questionService);
                case 5 -> fetchAll(questionService);
                case 6 -> fetchByQuizId(scanner, questionService);
                case 7 -> fetchBySubjectId(scanner, questionService);
                case 8 -> exit = true;
                default -> System.out.println("❌ Invalid option!");
            }
        }

        sessionFactory.close();
        scanner.close();
        System.out.println("✅ Exited successfully.");
    }

    private static void saveQuestion(Scanner scanner, QuestionService service) {
        try {
            System.out.print("Enter Question Text: ");
            String text = scanner.nextLine();
            System.out.print("Option A: ");
            String a = scanner.nextLine();
            System.out.print("Option B: ");
            String b = scanner.nextLine();
            System.out.print("Option C: ");
            String c = scanner.nextLine();
            System.out.print("Option D: ");
            String d = scanner.nextLine();
            System.out.print("Correct Option (A/B/C/D): ");
            char correct = scanner.nextLine().toUpperCase().charAt(0);

            System.out.print("Enter Quiz ID: ");
            int quizId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Subject ID: ");
            int subjectId = Integer.parseInt(scanner.nextLine());

            // Minimal Quiz and Subject objects (only IDs)
            Quiz quiz = new Quiz();
            quiz.setId(quizId);
            Subject subject = new Subject();
            subject.setId(subjectId);

            Question question = new Question(subject, quiz, text, a, b, c, d, correct);
            service.saveQuestion(question);
            System.out.println("✅ Question saved successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void updateQuestion(Scanner scanner, QuestionService service) {
        System.out.print("Enter Question ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Question q = service.findQuestionById(id);
        if (q != null) {
            System.out.print("Enter new Question Text: ");
            q.setQuestionText(scanner.nextLine());
            service.updateQuestion(q);
            System.out.println("✅ Updated successfully!");
        } else {
            System.out.println("❌ Question not found.");
        }
    }

    private static void deleteQuestion(Scanner scanner, QuestionService service) {
        System.out.print("Enter Question ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Question q = service.findQuestionById(id);
        if (q != null) {
            service.deleteQuestion(q);
            System.out.println("✅ Deleted successfully!");
        } else {
            System.out.println("❌ Question not found.");
        }
    }

    private static void fetchById(Scanner scanner, QuestionService service) {
        System.out.print("Enter Question ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Question q = service.findQuestionById(id);
        if (q != null) printQuestion(q);
        else System.out.println("❌ Question not found.");
    }

    private static void fetchAll(QuestionService service) {
        List<Question> list = service.getAllQuestions();
        if (list.isEmpty()) System.out.println("❌ No questions found.");
        else list.forEach(TestQuestion::printQuestion);
    }

    private static void fetchByQuizId(Scanner scanner, QuestionService service) {
        System.out.print("Enter Quiz ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        List<Question> list = service.getQuestionsByQuizId(id);
        if (list.isEmpty()) System.out.println("❌ No questions found for this quiz.");
        else list.forEach(TestQuestion::printQuestion);
    }

    private static void fetchBySubjectId(Scanner scanner, QuestionService service) {
        System.out.print("Enter Subject ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        List<Question> list = service.getQuestionsBySubject(id);
        if (list.isEmpty()) System.out.println("❌ No questions found for this subject.");
        else list.forEach(TestQuestion::printQuestion);
    }

    private static void printQuestion(Question q) {
        System.out.println("\nID: " + q.getId());
        System.out.println("Question: " + q.getQuestionText());
        System.out.println("A: " + q.getOptionA());
        System.out.println("B: " + q.getOptionB());
        System.out.println("C: " + q.getOptionC());
        System.out.println("D: " + q.getOptionD());
        System.out.println("Correct Option: " + q.getCorrectOption());
        System.out.println("Quiz: " + (q.getQuiz() != null ? q.getQuiz().getTitle() : "N/A"));
        System.out.println("Subject: " + (q.getSubject() != null ? q.getSubject().getName() : "N/A"));
    }
}
