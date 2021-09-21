package com.spring.mathapp.services;

import com.spring.mathapp.exceptions.UserNotFoundException;
import com.spring.mathapp.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {

    private User user;
    private User user2;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        user = new User("userTest", "passTest", "test@gmail.com", "nameTest",
                "lNameTest", null, null, null);

        user2 = new User("user2Test", "pass2Test", "test2@gmail.com", "name2Test",
                "lName2Test", null, null, null);

        userService.saveAll(List.of(user, user2));
    }

    @Test
    void save() {
        User testUser = new User("testMyUser", "pass", "testing@test.test", "test",
                "test", null, null, null);
        userService.save(testUser);

        assertNotNull(testUser, "Cannot save user!");
    }

    @Test
    void saveAllTest() {
        assertTrue(user.getId() != null && user2.getId() != null, "User not created!");
    }

    @Test
    void verifyUsersIds() {
        assertNotEquals(user.getId(), user2.getId(), "Users id has not auto increment!");
    }

    @Test
    void findUserById() {
        long id = user.getId();
        Assert.notNull(userService.findById(id), "Failed to find user by id!");
    }

    @Test
    void findUserByUsername() {
        String username = user.getUserName();
        Assert.notNull(userService.findByUsername(username), "Failed to find user by username!");
    }

    @Test
    void getUserByEmail() {
        String email = user.getEmail();
        Assert.notNull(userService.getUserByEmail(email), "Cannot find user email!");
    }

    @Test
    void findAllUsers() {
        List<User> users = userService.findAll();
        Assert.noNullElements(users, "Cannot get the list of users!");
    }

    @Test
    void deleteUserById() throws UserNotFoundException {
        Long id = user.getId();
        userService.deleteById(id);
        assertThrows(UserNotFoundException.class, () -> userService.findById(id), "Cannot delete user!");
    }

    @Test
    void deleteAllUsers() throws UserNotFoundException {
        userService.deleteAll(userService.findAll());

        assertThrows(UserNotFoundException.class, () -> List.of(userService.findById(user.getId()),
                userService.findById(user2.getId())), "Cannot delete user2!");

    }

}