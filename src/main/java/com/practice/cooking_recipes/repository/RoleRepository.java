package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findByName(String name);
}
