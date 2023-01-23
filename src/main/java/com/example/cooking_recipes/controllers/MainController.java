package com.example.cooking_recipes.controllers;

import com.example.cooking_recipes.models.Recipe;
import com.example.cooking_recipes.repo.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private RecipesRepository recipesRepository;

    @GetMapping("/")
    public String main(Model model, @RequestParam(value = "search", required = false ) String search) {
        Iterable<Recipe> allRecipes = recipesRepository.findAll();
        List<Recipe> neededRecipes = new ArrayList<>();
        for (Recipe recipe: allRecipes) {
            if (search != null && (Arrays.equals(Arrays.stream(recipe.getIngredients().toLowerCase().split(",")).sorted().toArray(String[]::new),
                    Arrays.stream(search.toLowerCase().split(",")).sorted().toArray(String[]::new))
                    || search.equals("*"))) {

                neededRecipes.add(recipe);
            }
        }
        model.addAttribute("recipes", neededRecipes);
        return "main";
    }
}

