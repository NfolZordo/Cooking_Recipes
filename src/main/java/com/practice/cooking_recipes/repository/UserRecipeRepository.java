package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.UserRecipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRecipeRepository extends CrudRepository<UserRecipe, Long> {

    @Query(value =
            "SELECT * " +
                    "FROM user_recipe " +
                    "WHERE user_id = :userId AND recipe_id = :recipeId", nativeQuery = true)
    List<UserRecipeRepository> repeatCheck(
            @Param("userId") Long userId,
            @Param("recipeId") Long recipeId
    );
}
