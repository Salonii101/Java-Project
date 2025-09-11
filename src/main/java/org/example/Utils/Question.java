//package org.example.Utils;
//
//public class Question {
//    String question;
//    String[] options;
//    int correctAnswerIndex;
//
//    public Question(String question, String[] options, int correctAnswerIndex) {
//        this.question = question;
//        this.options = options;
//        this.correctAnswerIndex = correctAnswerIndex;
//    }
//
//    public void displayQuestion() {
//        System.out.println(question);
//        for (int i = 0; i < options.length; i++) {
//            System.out.println((i + 1) + ". " + options[i]);
//        }
//    }
//
//    public boolean isCorrect(int userAnswer) {
//        return userAnswer == correctAnswerIndex + 1;
//    }
//
//    public String getCorrectAnswer() {
//        return options[correctAnswerIndex];
//    }
//}
//
