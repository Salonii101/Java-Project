package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment PK
    @Column(name = "id")
    private int id;
        public void setId(int id) {
            this.id = id;
        }

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    // One subject can have many quizzes
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    // Constructors
    public Subject() {}

    public Subject(String name) {
        this.name = name;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Quiz> getQuizzes() { return quizzes; }
    public void setQuizzes(List<Quiz> quizzes) { this.quizzes = quizzes; }
}
