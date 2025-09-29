package org.example.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment PK
    @Column(name = "id")
    private int id;

    // Relation to User
    @ManyToOne(fetch = FetchType.LAZY)   // Many results can belong to one user
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relation to Quiz
    @ManyToOne(fetch = FetchType.LAZY)   // Many results can belong to one quiz
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "taken_at", nullable = false)
    private LocalDateTime takenAt;

    // Constructors
    public Result() {}

    public Result(User user, Quiz quiz, int score, LocalDateTime takenAt) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
        this.takenAt = takenAt;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public LocalDateTime getTakenAt() { return takenAt; }
    public void setTakenAt(LocalDateTime takenAt) { this.takenAt = takenAt; }
}
