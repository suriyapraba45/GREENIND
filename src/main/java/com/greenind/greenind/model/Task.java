package com.greenind.greenind.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category; // 🌳 Tree & Nature, ♻ Waste Management, etc.

    private Integer points;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Submission> submissions;

    // Getters & Setters
}
