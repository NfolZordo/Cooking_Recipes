package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.Recipe;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecipesRepositoryCustomImpl implements RecipesRepositoryCustom {

    @Override
    public List<Recipe> findNedRecipe(Set<String> ingredients, Set<String> category, EntityManager entityManager) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = cb.createQuery(Recipe.class);
        Root<Recipe> recipe = query.from(Recipe.class);
        Path<String> ingredientsPath = recipe.get("ingredients");
        List<Predicate> predicates = new ArrayList<>();
        for (String ing : ingredients) {
            predicates.add(cb.like(ingredientsPath, ing));
        }
        predicates.add(recipe.get("category").in(category));
        query.select(recipe).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
