package com.spring.mathapp.configuration;

import com.spring.mathapp.models.Country;
import com.spring.mathapp.models.Role;
import com.spring.mathapp.models.User;
import com.spring.mathapp.services.CountryService;
import com.spring.mathapp.services.RoleService;
import com.spring.mathapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.Month.*;

@Configuration
class UserConfig {

    @Autowired
    RoleService roleService;

    @Autowired
    CountryService countryService;

    @Bean
    CommandLineRunner userRunner(UserService userService) {
        return args -> {
            User darius96, alex98, bannedUser, editorUser;

            Role roleAdmin = roleService.findRoleByName("ADMIN");
            Role roleEditor = roleService.findRoleByName("EDITOR");

            Country countryRo = countryService.findCountryByName("Romania");
            Country countryUk = countryService.findCountryByName("United Kingdom");

            darius96 = new User(1L, "Darius96", "password", "dariustinculescu@gmail.com",
                    "Darius", "Tinculescu",
                    true, null, countryRo, Set.of(roleAdmin));

            darius96.addDetails("ddd", LocalDate.of(1996, JULY, 26));

            alex98 = new User(2L, "Alex98", "password", "alex@gmail.com", "alex",
                    "mihai",null,
                    null, countryUk, null);

            alex98.addDetails("details", LocalDate.of(1990, JUNE, 22));

            bannedUser = new User(3L, "Banned-User", "password", "bannedUser@gmail.com",
                    "Banned", "Banned",
                    false, null, countryRo, null);

            bannedUser.addDetails("fff", LocalDate.of(1998, JANUARY, 19));

            editorUser = new User(4L, "Editor", "password", "editor@gmail.com",
                    "eDiToR", "editor",
                    null, null, countryUk, Set.of(roleEditor));

            editorUser.addDetails("bbb", LocalDate.of(1999, DECEMBER, 11));

            List<User> defaultUsers = List.of(darius96, alex98, bannedUser, editorUser);

            for (User user : defaultUsers) {
                Optional<User> userOptional = Optional.ofNullable(userService.findByUsername(user.getUserName()));
                if (userOptional.isEmpty()) {
                    userService.save(user);
                }
            }

        };
    }
}
