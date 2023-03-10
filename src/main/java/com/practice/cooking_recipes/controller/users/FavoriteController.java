package com.practice.cooking_recipes.controller.users;

import com.practice.cooking_recipes.model.Recipe;
import com.practice.cooking_recipes.model.User;
import com.practice.cooking_recipes.model.UserRecipe;
import com.practice.cooking_recipes.repository.RecipesRepository;
import com.practice.cooking_recipes.repository.UserRecipeRepository;
import com.practice.cooking_recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FavoriteController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipesRepository recipesRepository;

    @Autowired
    UserRecipeRepository userRecipeRepository;

    @PostMapping("/addToFavorite")
    public String addToFavorite(@RequestParam("recipeId") Integer recipeId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByEmail(currentUserName).get();
        Recipe recipe = recipesRepository.findRecipeById(recipeId);
        UserRecipe userRecipe = new UserRecipe(user,recipe);
        List<UserRecipeRepository> repeatCheck = userRecipeRepository.repeatCheck(user.getId(),recipeId);
        if(repeatCheck.size() == 0) {
            userRecipeRepository.save(userRecipe);
            return "recipe added";
        }
        return "repeat recipe";
    }

    @PostMapping("/deleteFromFavorite")
    public String deleteFromFavorite(@RequestParam("recipeId") Integer recipeId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByEmail(currentUserName).get();
        userRecipeRepository.deleteFavorite(user.getId(),recipeId);
        return "true";
    }
}
