package com.practice.cooking_recipes.controller.privileged;

import com.practice.cooking_recipes.model.Recipe;
import com.practice.cooking_recipes.repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/moder")
@Controller
public class ModeratorController {

    @Autowired
    RecipesRepository recipesRepository;

    @GetMapping("")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getCreateRecipePage() {
        return "createRecipe";
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String createRecipe(Model model, HttpServletRequest request) {

        Recipe checkRepeat;
        try {
            checkRepeat = recipesRepository.findRecipeByName(request.getParameter("name"));
        } catch (Exception e) {
            model.addAttribute("error", "Рецепт з такою назвою уже існує");
            return "createRecipe";
        }
        if (checkRepeat != null) {
            model.addAttribute("error", "Рецепт з такою назвою уже існує");
            return "createRecipe";
        }

        Recipe recipe = new Recipe(request.getParameter("name"),
                request.getParameter("recipe"),
                request.getParameter("ingredients"));
        recipesRepository.save(recipe);
        return "createRecipe";
    }

}
