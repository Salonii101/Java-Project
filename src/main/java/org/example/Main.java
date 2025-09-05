import org.example.models.Quiz;
import org.example.models.User;
import org.example.services.QuizService;
import org.example.services.ResultService;
import org.example.services.SubjectService;
import org.example.services.UserService;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        SubjectService subjectService = new SubjectService();
        QuizService quizService = new QuizService();
        ResultService resultService = new ResultService();

        System.out.println("1. Register\n2. Login");
        int choice = sc.nextInt(); sc.nextLine();

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
            return;
        }

        // Show subjects
        System.out.println("Choose a subject:");
        var subjects = subjectService.getAllSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i+1) + ". " + subjects.get(i).getName());
        }
        int subChoice = sc.nextInt();
        int subjectId = subjects.get(subChoice-1).getId();

        Quiz quiz = quizService.startQuiz(subjectId, "Maths Quiz");
        int score = 0;

        for (var q : quizService.getQuestionsForSubject(subjectId)) {
            System.out.println(q.getQuestionText());
            // (use timer logic here from earlier step)
        }

        resultService.saveResult(loggedInUser.getId(), quiz.getId(), score);
        System.out.println("Your score: " + score);
    }
}
