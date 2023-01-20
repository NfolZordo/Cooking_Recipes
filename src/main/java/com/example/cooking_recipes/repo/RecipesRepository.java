package com.example.cooking_recipes.repo;

import com.example.cooking_recipes.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipesRepository extends CrudRepository<Recipe, Integer> {

}
