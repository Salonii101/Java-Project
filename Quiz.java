import java.util.*;

public class Quiz {
    @SuppressWarnings("FieldMayBeFinal")
    private List<Question> questions;
    @SuppressWarnings("FieldMayBeFinal")
    private Map<Question, Integer> userAnswers = new LinkedHashMap<>();
    private int score = 0;

    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public void startQuiz(int timePerQuestionSeconds) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Quiz is starting... You have " + timePerQuestionSeconds + " seconds per question!");
        Utils.printLine();

        for (Question q : questions) {
            q.displayQuestion();
            System.out.print("Your answer (1-4): ");

            int answer = Utils.getUserAnswerWithTimeout(sc, timePerQuestionSeconds);
            userAnswers.put(q, answer);

            if (q.isCorrect(answer)) {
                score++;
                System.out.println("  Correct!");
            } else if (answer == -1) {
                System.out.println(" No answer (Timed out)");
            } else {
                System.out.println(" Wrong!");
            }

            Utils.printLine();
            Utils.sleep(1000); // brief pause before next question
        }
    }

    public void showScore() {
        System.out.println(" Quiz Finished!");
        Utils.printLine();
        System.out.println(" Your Score: " + score + " out of " + questions.size());
        Utils.printLine();
    }

    public void reviewAnswers() {
        System.out.println("\n Answer Review:");
        Utils.printLine();

        for (Question q : questions) {
            System.out.println("Q: " + q.question);

            int userAnswer = userAnswers.get(q);
            if (userAnswer > 0 && userAnswer <= q.options.length) {
                System.out.println("Your Answer: " + q.options[userAnswer - 1]);
            } else {
                System.out.println("Your Answer: No Answer");
            }

            System.out.println("Correct Answer: " + q.getCorrectAnswer());
            Utils.printLine();
        }
    }

    public int getScore() {
    return score;
}

public Map<Question, Integer> getUserAnswers() {
    return userAnswers;
}

}
