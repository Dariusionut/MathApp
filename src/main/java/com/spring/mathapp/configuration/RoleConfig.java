package com.spring.mathapp.configuration;

import com.spring.mathapp.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RoleConfig {

    @Bean
    CommandLineRunner roleRunner(RoleService roleService) {
        return args -> roleService.setDefaultRoles();

    }
}
