package com.spring.mathapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleTest {

    private Role roleTest;
    private User userTest;

    @BeforeEach
    void setUp() {
        userTest = new User();
        userTest.setUserName("UserTest");

        roleTest = new Role(1L, "role", List.of(userTest));
    }

    @Test
    void setName() {
        assertNotNull(roleTest.getName(), "roleTest.setName() does not work!");
    }

    @Test
    void getId() {
        assertEquals(roleTest.getId(), 1L, "getId does not work!");
    }

    @Test
    void getName() {
        assertEquals(roleTest.getName(), "ROLE", "getName() does not work!");
    }

    @Test
    void getUsers() {
        assertNotNull(roleTest.getUsers(), "Cannot get the list of users!");
    }

    @Test
    void setId() {
        roleTest.setId(5L);
        assertEquals(5, (long) roleTest.getId(), "setId() does not work!");
    }

    @Test
    void setUsers() {
        User newUserTest = new User();
        newUserTest.setUserName("NewUserTest");
        roleTest.setUsers(List.of(newUserTest));

        assertEquals(roleTest.getUsers().contains(newUserTest), true, "setUsers() does not work!");
    }

    @Test
    void testToString() {
        assertEquals(roleTest.toString(), roleTest.getName(), "roleTest.toString() does not work properly!");
    }

}

