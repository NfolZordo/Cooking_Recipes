package com.practice.cooking_recipes.security;

import com.practice.cooking_recipes.model.Role;
import com.practice.cooking_recipes.model.User;
import com.practice.cooking_recipes.repository.UserRepository;
import com.practice.cooking_recipes.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        List<Role> roles = new ArrayList<>();

        String rol = userRoleRepository.getRoleqqq("q3").toString();
        if (rol.indexOf('1') > 0) {
            roles.add(new Role(Long.valueOf(1),"ADMIN"));
        }
        if (rol.indexOf('2') > 0) {
            roles.add(new Role(Long.valueOf(2),"USER"));
        }
        if (rol.indexOf('3') > 0) {
            roles.add(new Role(Long.valueOf(3),"MODERATOR"));
        }

        Set<SimpleGrantedAuthority> roleSet = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return SecurityUser.fromUser(user, roleSet );
    }
}
