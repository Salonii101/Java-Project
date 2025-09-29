package org.example.models;

import jakarta.persistence.*;


@Entity
@Table(name = "questions") // lowercase to follow standard naming
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER) // always good to specify fetch type
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject; // FK to Subject

    @Column(name = "question_text", length = 255, nullable = false)
    private String questionText;

    @Column(name = "option_a", length = 50, nullable = false)
    private String optionA;

    @Column(name = "option_b", length = 50, nullable = false)
    private String optionB;

    @Column(name = "option_c", length = 50, nullable = false)
    private String optionC;

    @Column(name = "option_d", length = 50, nullable = false)
    private String optionD;

    @Column(name = "correct_option", length = 1, nullable = false)
    private char correctOption;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id", nullable = false) // FK to Quiz
    private Quiz quiz;


    // Getters & Setters
    public int getId() { return id; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public char getCorrectOption() { return correctOption; }
    public void setCorrectOption(char correctOption) { this.correctOption = correctOption; }
}
