package com.greenind.greenind.controller;

import com.greenind.greenind.model.Student;
import com.greenind.greenind.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1️⃣ Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "student/student-login";
    }

    // 2️⃣ Handle login submission
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String studentId,
            @RequestParam String password,
            Model model
    ) {
        Student student = studentRepository.findByStudentId(studentId);

        if (student != null && student.getPassword().equals(password)) {

            model.addAttribute("studentName", student.getFullName());
            model.addAttribute("studentId", student.getStudentId());

            return "student/student-dashboard";

        } else {
            model.addAttribute("error", "Invalid Student ID or Password!");
            return "student/student-login";
        }
    }

    // 3️⃣ Show registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "student/student-register";
    }

    // 4️⃣ Handle registration submission
    @PostMapping("/register")
    public String processRegister(
            @RequestParam String studentId,
            @RequestParam String fullName,
            @RequestParam String password,
            Model model
    ) {
        if (studentRepository.findByStudentId(studentId) != null) {
            model.addAttribute("error", "Student ID already exists!");
            return "student/student-register";
        }

        Student student = new Student();
        student.setStudentId(studentId);
        student.setFullName(fullName);
        student.setPassword(password);

        studentRepository.save(student);

        model.addAttribute("success", "Registered successfully! Please login.");
        return "student/student-login";
    }
}
