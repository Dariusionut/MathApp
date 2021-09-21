package com.spring.mathapp.services;

import com.spring.mathapp.exceptions.UserDetailsNotFoundException;
import com.spring.mathapp.models.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class DetailsServiceTest {

    private Details userDetails;

    @Autowired
    private DetailsService detailsService;

    @BeforeEach
    void setUp() {
        userDetails = new Details();
        userDetails.setInfo("user details test");

        detailsService.save(userDetails);
    }

    @Test
    void createUserDetails() {
        Assert.isTrue(userDetails.getId() != null, "User Details not created!");
    }

    @Test
    void findAllUserDetails() {
        Assert.noNullElements(detailsService.findAll(), "User List is empty!");
    }

    @Test
    void findUserDetailsById() {
        Long id = userDetails.getId();
        Assert.notNull(detailsService.findById(id), "Failed to retrieve User Details!");
    }

    @Test
    void deleteUserById() {
        long id = userDetails.getId();
        detailsService.deleteById(id);
        assertThrows(UserDetailsNotFoundException.class, () -> detailsService.findById(id), "Failed to delete User Details!");
    }

}