package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.UserRecipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRecipeRepository extends CrudRepository<UserRecipe, Long> {
    @Query(value =
            "INSERT INTO public.user_recipe( " +
                    "user_id, recipe_id) " +
                    "VALUES ( :userId, :recipeId);", nativeQuery = true)
    void addUserRecipe(
            @Param("userId") Long userId,
            @Param("recipeId") Long recipeId
    );
}
