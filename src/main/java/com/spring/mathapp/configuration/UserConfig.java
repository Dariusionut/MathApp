package com.spring.mathapp.configuration;

import com.spring.mathapp.models.Role;
import com.spring.mathapp.models.User;
import com.spring.mathapp.services.RoleService;
import com.spring.mathapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Configuration
class UserConfig {

    @Autowired
    RoleService roleService;

    @Bean
    CommandLineRunner userRunner(UserService userService) {
        return args -> {

            Role roleAdmin = roleService.findRoleByName("ADMIN");
            Role roleEditor = roleService.findRoleByName("EDITOR");

            User darius96 = new User(1L, "Darius96", "password", "dariustinculescu@gmail.com", "Darius",
                    "Tinculescu", true, null, Set.of(roleAdmin));

            User alex98 = new User(2L, "Alex98", "password", "alex@gmail.com", "alex",
                    "mihai", null, null, null);

            User bannedUser = new User(3L, "Banned-User", "password", "bannedUser@gmail.com",
                    "Banned", "Banned", false, null, null);

            User editorUser = new User(4L, "Editor", "password", "editor@gmail.com",
                    "eDiToR", "editor", null, null, Set.of(roleEditor));

            List<User> defaultUsers = List.of(darius96, alex98, bannedUser, editorUser);

            defaultUsers.forEach(user -> {
                Optional<User> userOptional = Optional.ofNullable(userService.findByUsername(user.getUserName()));
                if (userOptional.isEmpty()) {
                    userService.save(user);
                }
            });

        };
    }
}
