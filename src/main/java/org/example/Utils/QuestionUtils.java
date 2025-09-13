package org.example.Utils;

import org.example.models.Question;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for handling Question-related operations.
 * This is not an entity class, but provides helper methods
 * for shuffling, formatting, and validating questions.
 */
public class QuestionUtils {

    public static List<Question> shuffleQuestions(List<Question> questions) {
        Collections.shuffle(questions);
        return questions;
    }

    public static String formatQuestion(Question question) {
        StringBuilder sb = new StringBuilder();
        sb.append("Q").append(question.getId())
          .append(": ").append(question.getQuestionText()).append("\n")
          .append("A) ").append(question.getOptionA()).append("\n")
          .append("B) ").append(question.getOptionB()).append("\n")
          .append("C) ").append(question.getOptionC()).append("\n")
          .append("D) ").append(question.getOptionD()).append("\n");
        return sb.toString();
    }

    public static boolean validateAnswer(Question question, String answer) {
        if (answer == null) {
            return false;
        }
        return Character.toUpperCase(question.getCorrectOption()) ==
               Character.toUpperCase(answer.trim().charAt(0));
    }
}
