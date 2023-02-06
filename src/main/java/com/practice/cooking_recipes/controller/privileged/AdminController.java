package com.practice.cooking_recipes.controller.privileged;

import com.practice.cooking_recipes.model.User;
import com.practice.cooking_recipes.repository.RoleRepository;
import com.practice.cooking_recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    //    @PreAuthorize(true)
//    @ResponseBody
    @GetMapping("")
    @PreAuthorize("hasAuthority(\"ADMIN\")")
    public String getAdminPage() {
        return "admin";
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority(\"ADMIN\")")
    public String doModer(Model model, @RequestParam("email") String email) {

        User user;
        try {
            user = userRepository.findByEmail(email).get();
        }catch (Exception e) {
            model.addAttribute("error", "Користувача з такою адресою не інує");
            return "/admin";
        }
        user.getRoles().contains("MODERATOR");
        if (user.getRoles().contains("MODERATOR")) {
            model.addAttribute("error", "Користувача з уже являється модератором");
            return "/admin";

        }
        user.addRoles(roleRepository.findByName("MODERATOR"));

        userRepository.save(user);
        model.addAttribute("error", "Користувача " + user.getEmail() + " призначено модератором");
        return "/admin";
    }

}
