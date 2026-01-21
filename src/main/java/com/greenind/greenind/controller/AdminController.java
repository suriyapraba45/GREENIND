package com.greenind.greenind.controller;

import com.greenind.greenind.model.Admin;
import com.greenind.greenind.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    // 1️⃣ Show admin login form
    @GetMapping("/login")
    public String showAdminLoginForm() {
        return "admin/admin-login"; // Thymeleaf template
    }

    // 2️⃣ Handle login form submission
    @PostMapping("/login")
    public String processAdminLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        Admin admin = adminRepository.findByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            // Login successful
            model.addAttribute("adminName", admin.getFullName());
            // Add dashboard data if needed
            model.addAttribute("totalStudents", 10); // example
            model.addAttribute("pendingSubmissions", 5); // example
            return "admin/admin-dashboard"; // Thymeleaf dashboard page
        } else {
            // Login failed
            model.addAttribute("error", "Invalid username or password!");
            return "admin/admin-login"; // back to login page
        }
    }

    // 3️⃣ Show admin registration form
    @GetMapping("/register")
    public String showAdminRegisterForm() {
        return "admin/admin-register"; // Thymeleaf template
    }

    // 4️⃣ Handle registration form submission
    @PostMapping("/register")
    public String registerAdmin(
            @RequestParam String username,
            @RequestParam String fullName,
            @RequestParam String password,
            Model model) {

        // Check if username already exists
        if (adminRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists!");
            return "admin/admin-register"; // stay on register page
        }

        // Save new admin
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setFullName(fullName);
        admin.setPassword(password); // for now plain text
        adminRepository.save(admin);

        model.addAttribute("success", "Admin registered successfully! Please login.");
        return "admin/admin-login"; // redirect to login page
    }
    @PostMapping("/login")
    public String processAdminLogin(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        Model model
)   {
    Admin admin = adminRepository.findByUsername(username);

    if (admin != null && admin.getPassword().equals(password)) {

        // SUCCESS → Go to Admin Dashboard
        model.addAttribute("adminName", admin.getUsername());
        return "admin/admin-dashboard";

    } else {

        // FAIL → Back to login page
        model.addAttribute("error", "Invalid Admin ID or Password!");
        return "admin/admin-login";
    }
}
}