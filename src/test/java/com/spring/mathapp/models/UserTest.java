package com.spring.mathapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    User user;
    Details userDetails;
    Role userRole;

    @BeforeEach
    void setUp() {
        userDetails = new Details();
        userDetails.setId(1L);
        userDetails.setInfo("testDetails!");

        userRole = new Role();
        userRole.setId(1L);
        userRole.setName("testRole");

        user = new User(1L, "testUsername", "testPass", "test@test.com",
                "firstNameTest", "lastNameTest", true, userDetails, Set.of(userRole));

    }

    @Test
    void getFullName() {
        String fullName = user.getFirstName().substring(0, 1).toUpperCase() +
                user.getFirstName().substring(1).toLowerCase() + " " + user.getLastName().substring(0, 1).toUpperCase() +
                user.getLastName().substring(1).toLowerCase();

        assertEquals(user.getFullName(), fullName, "Cannot get user fullName!");
    }

    @Test
    void getId() {
        assertNotNull(user.getId(), "Cannot get user's id!");
    }

    @Test
    void getUserName() {
        assertNotNull(user.getUserName(), "Cannot get username!");
    }

    @Test
    void getPassword() {
        assertNotNull(user.getPassword(), "Cannot get user's password!");
    }

    @Test
    void getEmail() {
        assertNotNull(user.getEmail(), "Cannot get user's email!");
    }

    @Test
    void getFirstName() {
        assertNotNull(user.getFirstName(), "Cannot get user's firstName!");
    }

    @Test
    void getLastName() {
        assertNotNull(user.getLastName(), "Cannot get user's lastName!");
    }

    @Test
    void getIsEnabled() {
        assertNotNull(user.getIsEnabled(), "Cannot get user's account status!");
    }

    @Test
    void getDetails() {
        assertNotNull(user.getDetails().getId(), "Cannot get user's details!");
    }

    @Test
    void getRole() {
        assertNotNull(user.getRoles(), "Cannot get user's role!");
    }

    @Test
    void setId() {
        user.setId(1L);
        assertEquals(user.getId(), 1L, "user.setId() does not work!");
    }

    @Test
    void setUserName() {
        user.setUserName("testUser");
        assertEquals(user.getUserName(), "testUser", "user.setUserName() does not work!");
    }

    @Test
    void setPassword() {
        user.setPassword("testPassword");
        assertEquals(user.getPassword(), "testPassword", "user.setPassword() does not work!");
    }

    @Test
     void setEncodedPassword() {
        String rawPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setEncodedPassword(user.getPassword());
        String encodedPassword = user.getPassword();

        boolean encodeSucceeds = encoder.matches(rawPassword, encodedPassword);

        assertTrue(encodeSucceeds, "userSetEncodedPassword() does not work!");
    }

    @Test
    void setEmail() {
        user.setEmail("test@test.test");
        assertEquals(user.getEmail(), "test@test.test", "user.setEmail() does not work!");
    }

    @Test
    void setFirstName() {
        user.setFirstName("testFirstName");
        assertEquals(user.getFirstName(), "testFirstName", "userSetFirstName() does not work!");
    }

    @Test
    void setLastName() {
        user.setLastName("testLastName");
        assertEquals(user.getLastName(), "testLastName", "userSetLastName() does not work!");
    }

    @Test
    void setIsEnabled() {
        user.setIsEnabled(true);
        assertTrue(user.getIsEnabled(), "userSetIsEnabled() does not work!");
    }

    @Test
    void setDetails() {
        Details newDetails = new Details(2L, "newDetails", user);
        user.setDetails(newDetails);
        assertEquals(user.getDetails(), newDetails, "Cannot set User details!");

    }

    @Test
    void addDetails() {
        String infoTest = "infoTest";
        user.addDetails(infoTest);
        assertSame("infoTest", user.getDetails().getInfo(), "Cannot add details!");

    }

    @Test
    void updateDetails() {

        user.updateDetails(1L, "updateDetailsTest");
        assertTrue(user.getId() == 1L && user.getDetails().getInfo() == "updateDetailsTest", "Cannot update details!");
    }

    @Test
    void setRole() {
        Role newRoleTest = new Role();
        newRoleTest.setId(2L);
        newRoleTest.setName("newRoleTest");

        user.setRoles(Set.of(newRoleTest));

        assertTrue(user.getRoles().contains(newRoleTest), "Cannot get user roles!");
    }

    @Test
    void toStringTest() {
        assertEquals(user.toString(), user.getUserName(), "Cannot get user.toString()!");
    }
}