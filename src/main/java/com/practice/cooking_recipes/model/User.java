package com.practice.cooking_recipes.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id" ),
    inverseJoinColumns = @JoinColumn(
                    name = "role_id"))
    private Collection<Role> roles = new HashSet<>();

    public void addRoles(Role roles) {
        this.roles.add(roles);
    }

    public User() {
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

        Collection<Role> userRole = new HashSet<>();
        userRole.add(new Role("USER"));
        this.roles = userRole;
    }
}
