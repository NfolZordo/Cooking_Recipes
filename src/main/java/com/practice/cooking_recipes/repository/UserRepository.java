package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.Recipe;
import com.practice.cooking_recipes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {

//    @Query(value =
//            "SELECT * " +
//            "FROM users " +
//            "WHERE email = :email", nativeQuery = true)
//    User findByEmail(
//            @Param("email") String email );
    User findByEmail(String email);

}
