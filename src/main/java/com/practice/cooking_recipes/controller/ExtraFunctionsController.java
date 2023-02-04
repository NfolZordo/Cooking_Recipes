package com.practice.cooking_recipes.controller;

import com.practice.cooking_recipes.repository.RecipesRepository;
import com.practice.cooking_recipes.repository.UserRecipeRepository;
import com.practice.cooking_recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ExtraFunctionsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipesRepository recipesRepository;

    @Autowired
    UserRecipeRepository userRecipeRepository;

    @GetMapping("/createListIngredients")
    public void createListIngredients() {
        Set<String> uniqueIngredients = new HashSet<>();
        List<String> allIngredients = recipesRepository.getAllIngredient();
        for (String ingredient: allIngredients) {
            Arrays.stream(ingredient.split(",")).forEach(i -> uniqueIngredients.add(i.toLowerCase()) );
        }
        try {
            FileWriter ingredientsFile = new FileWriter("src/main/resources/static/List_ingredients.txt");
            uniqueIngredients.forEach(a -> {
                try {
                    ingredientsFile.write(a + " | ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            ingredientsFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
