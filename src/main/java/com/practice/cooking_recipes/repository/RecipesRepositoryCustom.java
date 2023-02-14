package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.Recipe;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public interface RecipesRepositoryCustom {
    List<Recipe> findNedRecipe(Set<String> ingredients, Set<String> category, EntityManager entityManager);
}
