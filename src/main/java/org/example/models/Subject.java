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

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    // One subject can have many quizzes
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

}
