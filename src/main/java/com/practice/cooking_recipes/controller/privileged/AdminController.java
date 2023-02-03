package com.practice.cooking_recipes.controller.privileged;

import com.practice.cooking_recipes.model.Role;
import com.practice.cooking_recipes.model.User;
import com.practice.cooking_recipes.model.UserRole;
import com.practice.cooking_recipes.repository.RoleRepository;
import com.practice.cooking_recipes.repository.UserRepository;
import com.practice.cooking_recipes.repository.UserRoleRepository;
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

    @Autowired
    UserRoleRepository userRoleRepository;


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
//        if (userRepository.findByEmail(email).size() == 0)
        try {
            user = userRepository.findByEmail(email);
        }catch (Exception e) {
            model.addAttribute("error", "користувача з такою адресою не інує");
            return "/admin";
        }
        UserRole userRole = new UserRole(user.getId(),
                Long.valueOf(3));
        userRoleRepository.save(userRole);
        return "/admin";
    }

}
