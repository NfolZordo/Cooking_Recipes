package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.Recipe;
import com.practice.cooking_recipes.model.UserRecipe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipesRepository extends CrudRepository<Recipe, Integer>, JpaSpecificationExecutor<UserRecipe> {

    @Override
    List<UserRecipe> findAll(Specification<UserRecipe> spec);

    @Query(value =
            "SELECT  r.* " +
            "FROM user_recipe " +
            "JOIN users u ON user_recipe.user_id = u.id " +
            "JOIN recipe r ON user_recipe.recipe_id = r.id " +
            "WHERE u.email = :email ", nativeQuery = true)
    List<Recipe> findFavorRecipeByEmail(
            @Param("email") String email
    );
    @Query(value =
            "SELECT * " +
            "FROM public.recipe " +
            "WHERE recipe LIKE :ingredient ", nativeQuery = true)
    List<Recipe> findRecipeByIngredient(
            @Param("ingredient") String ingredient
    );
    @Query(value =
            "SELECT ingredients " +
            "FROM recipe; ", nativeQuery = true)
    List<String> getAllIngredient();
}
