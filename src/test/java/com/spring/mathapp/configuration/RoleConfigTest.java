package com.spring.mathapp.configuration;

import com.spring.mathapp.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RoleConfigTest {

    @Autowired
    RoleService roleService;

    boolean roleAdmin;
    boolean roleEditor;
    boolean roleUser;

    @BeforeEach
    void setUp() {
         roleAdmin = roleService.findRoleByName("ADMIN") != null;
         roleEditor = roleService.findRoleByName("EDITOR") != null;
         roleUser = roleService.findRoleByName("USER") != null;
    }

    @Test
    void roleRunnerTest() {

        assertTrue(roleAdmin && roleEditor && roleUser, "RoleRunner does not work!");
    }
}