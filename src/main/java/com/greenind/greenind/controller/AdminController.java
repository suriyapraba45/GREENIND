package com.greenind.greenind.controller;

import com.greenind.greenind.model.Admin;
import com.greenind.greenind.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    // 1️⃣ Show Admin Login Page
    @GetMapping("/login")
    public String showAdminLoginForm() {
        return "admin/admin-login";
    }

    // 2️⃣ Process Admin Login
    @PostMapping("/login")
    public String processAdminLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Admin admin = adminRepository.findByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {

            // SUCCESS → Dashboard
            model.addAttribute("adminName", admin.getFullName());
            model.addAttribute("totalStudents", 10);   // temporary
            model.addAttribute("pendingSubmissions", 5);

            return "admin/admin-dashboard";

        } else {

            // FAIL → Back to login
            model.addAttribute("error", "Invalid Admin ID or Password!");
            return "admin/admin-login";
        }
    }

    // 3️⃣ Show Admin Register Page
    @GetMapping("/register")
    public String showAdminRegisterForm() {
        return "admin/admin-register";
    }

    // 4️⃣ Process Admin Registration
    @PostMapping("/register")
    public String registerAdmin(
            @RequestParam String username,
            @RequestParam String fullName,
            @RequestParam String password,
            Model model) {

        if (adminRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists!");
            return "admin/admin-register";
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setFullName(fullName);
        admin.setPassword(password); // later encrypt

        adminRepository.save(admin);

        model.addAttribute("success", "Registration successful! Please login.");
        return "admin/admin-login";
    }
}