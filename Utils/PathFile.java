package javaQuizApp.Utils;
import javaQuizApp.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class PathFile {

    // ==========================
    //  FILE PATH CONFIGURATIONS
    // ==========================

    // Base directory for user-related files
    public static final String USER_DIR = "User/";

    // Base directory for question files
    public static final String QUESTION_DIR = "Question text files/";

    // Users database file
    public static final String USER_FILE = USER_DIR + "users.txt";

    // Get history file path for a user
    public static String getHistoryFile(String username) {
        return USER_DIR + username + "_history.txt";
    }

    // Get question file path for a subject
    public static String getQuestionFile(String subject) {
        switch (subject.toLowerCase()) {
            case "english":
                return QUESTION_DIR + "english.txt";
            case "maths":
                return QUESTION_DIR + "maths.txt";
            case "computer":
                return QUESTION_DIR + "computer.txt";
            default:
                throw new IllegalArgumentException("Unknown subject: " + subject);
        }
    }


    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public static int getUserAnswerWithTimeout(Scanner sc, int seconds) {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    // Prompt printed once outside the thread
    //System.out.print("Your answer (1-4): ");

    Future<Integer> future = executor.submit(() -> {
        while (!sc.hasNextInt()) {
            sc.next(); // skip invalid input
            System.out.print("Please enter a number between 1-4: ");
        }
        return sc.nextInt();
    });

    try {
        return future.get(seconds, TimeUnit.SECONDS); // Wait for input or timeout
    } catch (TimeoutException e) {
        System.out.println("\n Time's up!");
        return -1;
    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    } finally {
        executor.shutdownNow();
    }
}


    public static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static List<Question> loadQuestions(String filename) {
    List<Question> questions = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String questionText = line.substring(2); // remove "Q:"
            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                line = reader.readLine();
                options[i] = line.substring(2); // remove "A:"
            }
            line = reader.readLine(); // Correct answer
            int correctIndex = Integer.parseInt(line.substring(2)) - 1; // 0-based
            questions.add(new Question(questionText, options, correctIndex));
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

    Collections.shuffle(questions);
    return questions;
}


}
