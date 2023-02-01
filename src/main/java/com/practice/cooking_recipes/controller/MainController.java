package com.practice.cooking_recipes.controller;

import com.practice.cooking_recipes.model.Recipe;
import com.practice.cooking_recipes.repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {

    @Autowired
    private RecipesRepository recipesRepository;

    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "search", required = false ) String search) {
        List<Recipe> neededRecipes = new ArrayList<>();
        if (search == null) {
            return "main";
        }

        String searchUpgrade = Arrays.stream(search.toLowerCase().split(" ")).map(Objects::toString)
                .collect(Collectors.joining("%"));

        neededRecipes = recipesRepository.findRecipeByIngredient("%" + searchUpgrade + "%");

//        else if (typeSearch.equals("ALL")) {
//            Iterable<Recipe> allRecipes = recipesRepository.findAll();
//            for (Recipe recipe: allRecipes) {
//                if (Arrays.equals(Arrays.stream(recipe.getIngredients().toLowerCase().split(",")).sorted().toArray(String[]::new),
//                        Arrays.stream(search.toLowerCase().split(",")).sorted().toArray(String[]::new))
//                        || search.equals("*")) {
//
//                    neededRecipes.add(recipe);
//                }
//            }
//        }
        model.addAttribute("recipes", neededRecipes);
        return "main";
    }

    @ResponseBody
    @PostMapping("/search")
    public List<Recipe> getRecipe(Model model, @RequestParam("search") String[] search) {

        System.out.println(search);
        List<Recipe> neededRecipes = new ArrayList<>();
        String searchUpgrade = Arrays.stream(search).map(s->s.toLowerCase())
                .map(Objects::toString)
                .collect(Collectors.joining("%"));
        for (String s : search) {
            System.out.println(s);
        }

        neededRecipes = recipesRepository.findRecipeByIngredient("%" + searchUpgrade + "%");

        return neededRecipes;
    }

}
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//            return currentUserName;
//        }
