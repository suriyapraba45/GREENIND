package com.greenind.greenind.controller;

import com.greenind.greenind.model.Student;
import com.greenind.greenind.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "student/student-login";
    }

    // Handle login
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String studentId,
            @RequestParam String password,
            Model model) {

        Optional<Student> studentOpt =
                studentRepository.findByStudentIdAndPassword(studentId, password);

        if (studentOpt.isPresent()) {
            model.addAttribute("student", studentOpt.get());
            return "student/student-dashboard";
        } else {
            model.addAttribute("error", "Invalid Student ID or Password");
            return "student/student-login";
        }
    }

    // Show register page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "student/student-register";
    }

    // Handle register
    @PostMapping("/register")
    public String processRegister(
            @RequestParam String studentId,
            @RequestParam String fullName,
            @RequestParam String password,
            Model model) {

        if (studentRepository.findByStudentId(studentId).isPresent()) {
            model.addAttribute("error", "Student ID already exists");
            return "student/student-register";
        }

        Student student = new Student();
        student.setStudentId(studentId);
        student.setFullName(fullName);
        student.setPassword(password);
        student.setTotalPoints(0);

        studentRepository.save(student);

        model.addAttribute("success", "Registration successful. Please login.");
        return "student/student-login";
    }
}
