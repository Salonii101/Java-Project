package org.example.models;


import jakarta.persistence.*;

@Entity
@Table(name = "Questions")
public class Questions {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        @JoinColumn(name = "subject_id", nullable = false)
        private Subject subject;// FK to Subject
        @Column(name="questionText", length = 100 , nullable = false)
        private String questionText;

        @Column(name="OptionA",length = 2, nullable = false)
        private String optionA;
        @Column(name="OptionB",length = 2, nullable = false)
        private String optionB;
        @Column(name="OptionC",length = 2, nullable = false)
        private String optionC;
        @Column(name="OptionD",length = 2, nullable = false)
        private String optionD;
        @Column(name="CorrectOption",length = 2, nullable = false)
        private char correctOption;

        public Questions() {}
        public Questions(int id, Subject subject , String questionText,
                        String optionA, String optionB, String optionC, String optionD, char correctOption) {
            this.id = id;
            this.subject = subject;
            this.questionText = questionText;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.correctOption = correctOption;
        }

        // getters & setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubjectId() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public char getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(char correctOption) {
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }


}

