package com.practice.cooking_recipes.repository;

import com.practice.cooking_recipes.model.Role;
import com.practice.cooking_recipes.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    @Query(value =
            "SELECT * " +
            "FROM users_recipes " +
            "WHERE user_id = :userId AND id = :roleId", nativeQuery = true)
    List<UserRecipeRepository> repeatCheck(
            @Param("userId") Long userId,
            @Param("roleId") Integer roleId
    );

    @Query(value =
            "SELECT roles.* " +
                    "FROM roles " +
                    "JOIN users_roles ON users_roles.role_id = roles.id " +
                    "JOIN users ON users_roles.user_id = users.id " +
                    "WHERE users.email = :user_name", nativeQuery = true)
    List<Role> getRole(
            @Param("user_name") String user_name);

    @Query(value =
            "SELECT roles.* " +
                    "FROM roles " +
                    "JOIN users_roles ON users_roles.role_id = roles.id " +
                    "JOIN users ON users_roles.user_id = users.id " +
                    "WHERE users.email = :user_name", nativeQuery = true)
    List<String> getRoleqqq(
            @Param("user_name") String user_name);
}
//            "JOIN Roles ON users_roles.role_id = roles.id " +

