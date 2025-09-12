//package org.example ;
//
//import org.example.models.User;
//import org.example.services.UserService;
//import org.example.Utils.Auth ;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main2 {
//
//    private static final Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        while (true) {
//            System.out.println("=== Welcome to the Quiz Application ===");
//            System.out.println("1. Register");
//            System.out.println("2. Login");
//            System.out.println("3. Exit");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            if (choice == 1) {
//                Auth.register() ;
//            } else if (choice == 2) {
//                User user = UserService.login();
//                if (user != null) {
//                    showDashboard(user);
//                } else {
//                    System.out.println("Invalid credentials. Please try again.");
//                }
//            } else if (choice == 3) {
//                System.out.println("Thank you for using the Quiz Application. Goodbye!");
//                break;
//            } else {
//                System.out.println("Invalid option. Please choose again.");
//            }
//        }
//        scanner.close();
//    }
//
//    private static void showDashboard(User user) {
//        while (true) {
//            System.out.println("\n=== Dashboard ===");
//            System.out.println("Welcome, " + user.getName() + "!");
//            System.out.println("1. Take a Test");
//            System.out.println("2. View Results");
//            System.out.println("3. Logout");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            if (choice == 1) {
//                takeTest(user);
//            } else if (choice == 2) {
//                QuizService.viewResults(user);
//            } else if (choice == 3) {
//                System.out.println("Logged out successfully.");
//                break;
//            } else {
//                System.out.println("Invalid option. Please choose again.");
//            }
//        }
//    }
//
//    private static void takeTest(User user) {
//        List<Subject> subjects = SubjectService.getAllSubjects();
//
//        if (subjects.isEmpty()) {
//            System.out.println("No subjects available.");
//            return;
//        }
//
//        System.out.println("\nSelect a subject:");
//        for (int i = 0; i < subjects.size(); i++) {
//            System.out.println((i + 1) + ". " + subjects.get(i).getName());
//        }
//        System.out.print("Enter choice: ");
//        int choice = Integer.parseInt(scanner.nextLine());
//
//        if (choice < 1 || choice > subjects.size()) {
//            System.out.println("Invalid subject choice.");
//            return;
//        }
//
//        Subject selectedSubject = subjects.get(choice - 1);
//        System.out.println("You selected: " + selectedSubject.getName());
//
//        List<Quiz> questions = QuizService.getQuestionsBySubject(selectedSubject.getId(), 10);
//
//        if (questions.isEmpty()) {
//            System.out.println("No questions available for this subject.");
//            return;
//        }
//
//        int score = 0;
//        for (int i = 0; i < questions.size(); i++) {
//            Quiz q = questions.get(i);
//            System.out.println("\nQuestion " + (i + 1) + ": " + q.getQuestionText());
//            for (int j = 0; j < q.getOptions().size(); j++) {
//                System.out.println((j + 1) + ". " + q.getOptions().get(j));
//            }
//            System.out.print("Enter your answer (1-" + q.getOptions().size() + "): ");
//            int answer = Integer.parseInt(scanner.nextLine());
//
//            if (answer == q.getCorrectOption()) {
//                System.out.println("Correct!");
//                score++;
//            } else {
//                System.out.println("Wrong! Correct answer is: " + q.getCorrectOption());
//            }
//        }
//
//        System.out.println("\nTest Completed! Your Score: " + score + " out of " + questions.size());
//        QuizService.saveResult(user, selectedSubject, score);
//
//        while (true) {
//            System.out.println("\n1. Go to Dashboard");
//            System.out.println("2. Take another Test");
//            System.out.println("3. Logout");
//            System.out.print("Choose an option: ");
//            int postChoice = Integer.parseInt(scanner.nextLine());
//
//            if (postChoice == 1) {
//                return;
//            } else if (postChoice == 2) {
//                takeTest(user);
//                return;
//            } else if (postChoice == 3) {
//                System.out.println("Logged out.");
//                System.exit(0);
//            } else {
//                System.out.println("Invalid option. Please choose again.");
//            }
//        }
//    }
//}
