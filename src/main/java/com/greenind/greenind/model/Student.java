package com.greenind.greenind.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // primary key

    @Column(unique = true, nullable = false)
    private String studentId;   // login ID

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    private LocalDate dob;

    // Many students → One admin
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    // -------- GETTERS & SETTERS --------

    public Long getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}