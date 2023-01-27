package com.practice.cooking_recipes.controller;

import com.practice.cooking_recipes.model.User;
import com.practice.cooking_recipes.model.Recipe;
import com.practice.cooking_recipes.repository.RecipesRepository;
import com.practice.cooking_recipes.repository.UserRecipeRepository;
import com.practice.cooking_recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRecipeRepository userRecipeRepository;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findNedUser(currentUserName);

        List<Recipe> favorRecipes = recipesRepository.findFavorRecipeByEmail(currentUserName).stream().collect(Collectors.toList());

        model.addAttribute("recipes", favorRecipes);
        model.addAttribute("userName", user);
        return "home";
    }

}
