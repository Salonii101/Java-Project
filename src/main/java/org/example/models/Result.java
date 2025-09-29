package org.example.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "result")
public class Result {

    // Primary Key as UUID
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Many results can belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many results can belong to one quiz
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    // Score
    @Column(name = "score", nullable = false)
    private int score;

    // Timestamp of when the quiz was taken
    @Column(name = "taken_at", nullable = false)
    private LocalDateTime takenAt;

    // ===== Constructors =====
    public Result() {}

    public Result(User user, Quiz quiz, int score, LocalDateTime takenAt) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
        this.takenAt = takenAt;
    }

    // ===== Getters & Setters =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public LocalDateTime getTakenAt() { return takenAt; }
    public void setTakenAt(LocalDateTime takenAt) { this.takenAt = takenAt; }
}
