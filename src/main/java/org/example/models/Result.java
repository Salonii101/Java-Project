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
        public void setId(UUID id) {
            this.id = id;
        }

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

}
