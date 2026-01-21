package com.greenind.greenind.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String options; // JSON string of options

    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private LearningModule module;

    // Getters & Setters
}
