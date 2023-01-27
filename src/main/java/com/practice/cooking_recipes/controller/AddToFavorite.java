package com.practice.cooking_recipes.controller;

import com.practice.cooking_recipes.model.User;
import com.practice.cooking_recipes.repository.UserRecipeRepository;
import com.practice.cooking_recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AddToFavorite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRecipeRepository userRecipeRepository;

    @PostMapping("/addToFavorite")
    public void addToFavorite(Model model, HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        System.out.println(request.getParameter("recipeId"));
        User user = userRepository.findNedUser(currentUserName);
        userRecipeRepository.addUserRecipe(user.getId(), Long.parseLong(request.getParameter("recipeId")));

    }
}
