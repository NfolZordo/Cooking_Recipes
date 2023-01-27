package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value =
            "SELECT *  " +
            "FROM users " +
            "WHERE email = :email ", nativeQuery = true)
    User findNedUser(
            @Param("email") String email
    );
}
