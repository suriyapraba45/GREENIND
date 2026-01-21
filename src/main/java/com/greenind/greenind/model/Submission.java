package com.greenind.greenind.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private String proofPath; // Path of uploaded image

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(columnDefinition = "TEXT")
    private String remarks; // Admin remarks

    private LocalDateTime submittedAt = LocalDateTime.now();

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    // Getters & Setters
}
