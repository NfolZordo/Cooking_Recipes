package com.example.cooking_recipes.controllers;

import com.example.cooking_recipes.models.Recipe;
import com.example.cooking_recipes.repo.RecipesRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private RecipesRepository recipesRepository;

    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {

//        System.out.println(request.getParameter("search"));
        Iterable<Recipe> recipes = recipesRepository.findAll();

        model.addAttribute("recipes", recipes);
        return "main";
    }
}
