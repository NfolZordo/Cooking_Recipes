package com.practice.cooking_recipes.controller.users;

import com.practice.cooking_recipes.model.Role;
import com.practice.cooking_recipes.model.User;
import com.practice.cooking_recipes.model.UserRole;
import com.practice.cooking_recipes.repository.RoleRepository;
import com.practice.cooking_recipes.repository.UserRepository;
import com.practice.cooking_recipes.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(Model model, HttpServletRequest request) {

        try {
            User checkRepeat = userRepository.findByEmail(request.getParameter("email"));
        } catch (Exception e) {
            model.addAttribute("error", "email уже зайгятий");
            return "registration";
        }
        User user = new User(request.getParameter("email"),
                passwordEncoder.encode(request.getParameter("password")),
                request.getParameter("first_name"),
                request.getParameter("last_name"));
        userRepository.save(user);

        UserRole userRole = new UserRole(
                userRepository.findByEmail(request.getParameter("email")).getId(),
                Long.valueOf(2));
        userRoleRepository.save(userRole);

        model.addAttribute("error", null);
        return "redirect:/login";
    }
}
