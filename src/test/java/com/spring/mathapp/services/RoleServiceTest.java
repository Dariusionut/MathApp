package com.spring.mathapp.services;

import com.spring.mathapp.exceptions.MyRoleNotFoundException;
import com.spring.mathapp.models.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class RoleServiceTest {

    private Role role;

    @Autowired
    RoleService roleService;

    @BeforeEach
    void setUp() {
        role = new Role("testRole");
        roleService.save(role);
    }

    @Test
    void findAll() {
        assertNotNull(roleService.findAll(), "Failed to get roleList!");
    }

    @Test
    void findById() {
        assertNotNull(roleService.findById(role.getId()), "Failed to get roleById");
    }

    @Test
    void findRoleByName(){
        Role userRoleTest = roleService.findRoleByName("testRole");

        assertNotNull(userRoleTest, "Cannot find role by name!");
    }

    @Test
    void save() {
        assertNotNull(role.getId(), "Failed to create role!");
    }

    @Test
    void setDefaultRoles(){
        roleService.setDefaultRoles();

        Role userRole = roleService.findRoleByName("USER");
        Role editorRole = roleService.findRoleByName("EDITOR");
        Role adminRole = roleService.findRoleByName("ADMIN");

        List<Role> defaultRoles = List.of(userRole, editorRole, adminRole);

        assertNotNull(defaultRoles,"dsads");
    }

    @Test
    void deleteById() {
        long id = role.getId();
        roleService.deleteById(id);
        assertThrows(MyRoleNotFoundException.class, () -> roleService.findById(id), "Failed to delete Role!");
    }


}