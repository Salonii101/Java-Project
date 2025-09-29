package org.example.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // "user" reserved in SQL
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;
        public void setId(UUID id) {
            this.id = id;
        }

    @Column(name = "name", nullable = false, length = 100)
    private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;
        public String getHashPassword() {
            return hashPassword;
        }
        public void setHashPassword(String hashPassword) {
            this.hashPassword = hashPassword;
        }

    @Column(name = "role", nullable = false, length = 50)
    private String role;
        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }

    // Relationships
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Quiz> quizzes;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Result> results;

    // Constructors
    public User() {}

    public User(String name, String hashPassword, String role) {
        this.name = name;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getHashPassword() { return hashPassword; }
    public void setHashPassword(String hashPassword) { this.hashPassword = hashPassword; }


//    public List<Quiz> getQuizzes() { return quizzes; }
//    public void setQuizzes(List<Quiz> quizzes) { this.quizzes = quizzes; }

//    public List<Result> getResults() { return results; }
//    public void setResults(List<Result> results) { this.results = results; }
}
