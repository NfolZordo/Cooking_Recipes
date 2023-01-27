package com.practice.cooking_recipes.controller;

import com.practice.cooking_recipes.model.Role;
import com.practice.cooking_recipes.model.Status;
import com.practice.cooking_recipes.model.User;
import com.practice.cooking_recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public String createUser(HttpServletRequest request) {

        try {
            userRepository.findByEmail(request.getParameter("email"));
        } catch (Exception e) {
            return "registration";
        }
        User user = new User(request.getParameter("email"),
                passwordEncoder.encode(request.getParameter("password")),
                request.getParameter("first_name"),
                request.getParameter("last_name"),
                Role.USER, Status.ACTIVE);
        userRepository.save(user);

        return "redirect:/login";
    }
}
