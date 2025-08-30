package javaQuizApp ;
import javaQuizApp.Utils.Auth;
import javaQuizApp.Utils.PathFile;

import java.io.*;
import java.util.*;

public class QuizApp {

    public static void main(String[] args) {
        new File(PathFile.USER_DIR).mkdirs();
        new File(PathFile.QUESTION_DIR).mkdirs();
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        String currentUserId = "";
        String currentUserName = "";

        System.out.println("Welcome to Quiz App");
        System.out.println("1. Register\n2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            if (choice == 1) {
                currentUserId = Auth.register(scanner);
            }

            // Login until successful
            while (!loggedIn) {
                if (choice != 1) System.out.println("Please login:");
                loggedIn = Auth.login(scanner);
                if (loggedIn) {
                    currentUserId = Auth.getCurrentUserId();
                    currentUserName = Auth.getCurrentUserName();
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        Set<String> completedSubjects = new HashSet<>();
        Map<String, String> subjectFiles = Map.of(
                "E", "english.txt",
                "C", "computer.txt",
                "M", "maths.txt"
        );
        Map<String, String> subjectNames = Map.of(
                "E", "English",
                "C", "Computer",
                "M", "Maths"
        );

        while (true) {
            System.out.print("Choose a subject - (E) English, (C) Computer, (M) Maths: ");
            String subjectChoice = scanner.next().toUpperCase();

            if (!subjectFiles.containsKey(subjectChoice)) {
                System.out.println("Invalid choice! Try again.");
                continue;
            }

            if (completedSubjects.contains(subjectChoice)) {
                System.out.println("You have already completed this subject.");
                continue;
            }

            String subject = subjectNames.get(subjectChoice);
            //String fileName = subjectFiles.get(subjectChoice);
             //  Fetch question file from "Question text files/"
            String fileName = PathFile.getQuestionFile(subject);

            List<Question> questions = PathFile.loadQuestions(fileName);
            if (questions.isEmpty()) {
                System.out.println("No questions found for " + subject);
                continue;
            }

            Quiz quiz = new Quiz(questions);

            long startTime = System.currentTimeMillis();
            quiz.startQuiz(15);
            long endTime = System.currentTimeMillis();

            quiz.showScore();
            quiz.reviewAnswers();

            long totalSeconds = (endTime - startTime) / 1000;
            int totalQuestions = questions.size();
            int attempted = 0;

            for (int answer : quiz.getUserAnswers().values()) {
                if (answer != -1) attempted++;
            }

            double accuracy = attempted > 0 ? (quiz.getScore() * 100.0 / attempted) : 0;
            String report = String.format("User: %s (%s)\nSubject: %s | Score: %d/%d | Accuracy: %.2f%% | Time: %d sec",
                    currentUserName, currentUserId, subject, quiz.getScore(), totalQuestions, accuracy, totalSeconds);

            // Save report
            //String historyFile = currentUserId + "_history.txt";
            
            // Save history into "User text/history files/"
            String historyFile = PathFile.getHistoryFile(currentUserId);
            try (PrintWriter out = new PrintWriter(new FileWriter(historyFile, true))) {
                out.println(report);
                System.out.println("Score saved to " + historyFile);
            } catch (IOException e) {
                System.out.println("Failed to save score.");
            }

            completedSubjects.add(subjectChoice);
            PathFile.printLine();

            if (completedSubjects.size() < 3) {
                System.out.print("Do you want to take another quiz? (Y/N): ");
                String again = scanner.next().toUpperCase();
                if (!again.equals("Y")) break;
            } else {
                System.out.println("All quizzes completed.");
                break;
            }
        }

        System.out.println("All selected quizzes completed successfully!");
        System.out.println("Your score has been saved to your history file.");
        PathFile.printLine();
    }
}
