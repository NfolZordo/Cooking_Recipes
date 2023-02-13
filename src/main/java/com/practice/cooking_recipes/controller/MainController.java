package com.practice.cooking_recipes.controller;

import com.practice.cooking_recipes.model.Recipe;
import com.practice.cooking_recipes.repository.RecipesRepositoryCustomImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/")
    public String getMainPage(Model model) {
        return "main";
    }

    @ResponseBody
    @GetMapping("/search")
    public List<Recipe> getRecipe(Model model, @RequestParam("search") String[] search,
                                  @RequestParam("category") String[] category) {

        List<Recipe> neededRecipes = new ArrayList<>();
        String searchUpgrade = "%" + Arrays.stream(search).map(s->s.toLowerCase())
                .map(Objects::toString)
                .collect(Collectors.joining("%/%")) + "%";
        String[] a = searchUpgrade.split("/");
        Set<String> categorySet = new HashSet<String>(Arrays.asList(category));
        RecipesRepositoryCustomImpl recipesRepositoryCustom = new RecipesRepositoryCustomImpl();
        neededRecipes = recipesRepositoryCustom.findNedRecipe(new HashSet<String>(Arrays.asList(a)), categorySet, entityManager);

        return neededRecipes;
    }
}

