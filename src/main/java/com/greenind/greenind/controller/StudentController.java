package com.greenind.greenind.controller;

import com.greenind.greenind.model.Student;
import com.greenind.greenind.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "student/student-login"; // Thymeleaf template
    }

    // Handle login submission
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String studentId,
            @RequestParam String password,
            Model model
    ) {
        Optional<Student> studentOpt = studentRepository.findByStudentIdAndPassword(studentId, password);

        if (studentOpt.isPresent()) {
            model.addAttribute("student", studentOpt.get());
            return "student/student-dashboard"; // Thymeleaf dashboard
        } else {
            model.addAttribute("error", "Invalid credentials!");
            return "student/student-login"; // back to login
        }
    }

    // Show registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "student/student-register"; // Thymeleaf template
    }

    // Handle registration submission
    @PostMapping("/register")
    public String processRegister(
            @RequestParam String studentId,
            @RequestParam String fullName,
            @RequestParam String password,
            Model model
    ) {
        // Check if studentId exists
        if (studentRepository.findByStudentIdAndPassword(studentId, password).isPresent()) {
            model.addAttribute("error", "Student ID already exists!");
            return "student/student-register";
        }

        // Save new student
        Student student = new Student();
        student.setStudentId(studentId);
        student.setFullName(fullName);
        student.setPassword(password); // plain text for now
        studentRepository.save(student);

        model.addAttribute("success", "Registered successfully! Please login.");
        return "student/student-login";
    }
    @PostMapping("/login")
    public String processStudentLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        Student student = studentRepository.findByUsername(username);

        if (student != null && student.getPassword().equals(password)) {

            model.addAttribute("studentName", student.getUsername());

            return "student/student-dashboard";

        } else {

            model.addAttribute("error", "Invalid Username or Password!");
            return "student/student-login";
        }
    }
}