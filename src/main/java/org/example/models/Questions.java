package org.example.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name="question_text", length = 255 , nullable = false)
    private String questionText;

    @Column(name="option_a", length = 100, nullable = false)
    private String optionA;

    @Column(name="option_b", length = 100, nullable = false)
    private String optionB;

    @Column(name="option_c", length = 100, nullable = false)
    private String optionC;

    @Column(name="option_d", length = 100, nullable = false)
    private String optionD;

    @Column(name="correct_option", length = 2, nullable = false)
    private String correctOption;

    public Questions() {}

    public Questions(Subject subject , String questionText,
                     String optionA, String optionB, String optionC, String optionD, String correctOption) {
        this.subject = subject;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

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

    public String getCorrectOption() { return correctOption; }
    public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }
}
