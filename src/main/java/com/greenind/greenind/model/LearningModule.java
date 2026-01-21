package com.greenind.greenind.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "learning_module")
public class LearningModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moduleId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    // Getters & Setters
}
