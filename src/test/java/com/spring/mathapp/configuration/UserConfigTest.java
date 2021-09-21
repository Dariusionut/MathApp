package com.spring.mathapp.configuration;

import com.spring.mathapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class UserConfigTest {

    @Autowired
    UserService userService;

    boolean dariusUser;
    boolean alexUser;
    boolean bannedUser;
    boolean editorUser;

    @BeforeEach
    void setUp() {
        dariusUser = userService.findByUsername("Darius96") != null;
        alexUser = userService.findByUsername("Alex98") != null;
        bannedUser = userService.findByUsername("Banned-User") != null;
        editorUser = userService.findByUsername("Editor") != null;
    }

    @Test
    void userRunnerTest() {
        assertTrue(dariusUser && alexUser && bannedUser && editorUser, "userRunner does not work!");
    }
}