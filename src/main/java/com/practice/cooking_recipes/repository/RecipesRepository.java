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
    Recipe findRecipeById(Integer id);

    Recipe findRecipeByName(String name);

    @Query(value =
            "SELECT  r.* " +
            "FROM users_recipes " +
            "JOIN users u ON users_recipes.user_id = u.id " +
            "JOIN recipe r ON users_recipes.recipe_id = r.id " +
            "WHERE u.email = :email ", nativeQuery = true)
    List<Recipe> findFavorRecipeByEmail(
            @Param("email") String email

    );

    @Query(value =
            "SELECT  users_recipes.recipe_id " +
                    "FROM users_recipes " +
                    "JOIN users u ON users_recipes.user_id = u.id " +
                    "WHERE u.email = :email ", nativeQuery = true)
    List<Integer> findIdFavorRecipeByEmail(
            @Param("email") String email
    );

    @Query(value =
            "SELECT * " +
            "FROM recipe " +
            "WHERE ingredients LIKE :ingredient AND category IN :category", nativeQuery = true)
    List<Recipe> findRecipeByIngredient(
            @Param("ingredient") String ingredient,
            @Param("category") String[] category
    );

    @Query(value =
            "SELECT ingredients " +
            "FROM recipe; ", nativeQuery = true)
    List<String> getAllIngredient();
}
