package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment in DB
    @Column(name = "id")
    private int id;

    @Column(name = "subject_id", nullable = false) // FK to Subject
    private int subjectId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    // Constructors
    public Quiz() {}

    public Quiz(int subjectId, String title) {
        this.subjectId = subjectId;
        this.title = title;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
