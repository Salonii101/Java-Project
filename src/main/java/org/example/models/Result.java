package org.example.models;

import java.time.LocalDateTime;

public class Result {
    private int id;
    private int userId;   // FK to User
    private int quizId;   // FK to Quiz
    private int score;
    private LocalDateTime takenAt;

    public Result() {}
    public Result(int id, int userId, int quizId, int score, LocalDateTime takenAt) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.takenAt = takenAt;
    }

    // getters & setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDateTime takenAt) {
        this.takenAt = takenAt;
    }
}
