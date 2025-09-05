package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users") // "user" is reserved in SQL, so safer to use "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Result> results;

    // Constructors
    public User() {}

    public User(String name, String hashPassword, String role) {
        this.name = name;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHashPassword() { return hashPassword; }
    public void setHashPassword(String hashPassword) { this.hashPassword = hashPassword; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<Quiz> getQuizzes() { return quizzes; }
    public void setQuizzes(List<Quiz> quizzes) { this.quizzes = quizzes; }

    public List<Result> getResults() { return results; }
    public void setResults(List<Result> results) { this.results = results; }
}
