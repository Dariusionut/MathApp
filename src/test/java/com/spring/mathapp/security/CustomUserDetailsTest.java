package com.spring.mathapp.security;

import com.spring.mathapp.models.User;
import com.spring.mathapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CustomUserDetailsTest {

    CustomUserDetails customUserDetails;

    @Autowired
    UserService userService;


    @BeforeEach
    void setUp() {

        User user = userService.findByUsername("Darius96");
        customUserDetails = new CustomUserDetails(user);

    }

    @Test
    void getAuthorities() {
        Assert.notEmpty(customUserDetails.getAuthorities(), "Cannot get authorities!");
    }

    @Test
    void getPassword() {
        Assert.notNull(customUserDetails.getPassword(), "Cannot get user password!");
    }

    @Test
    void getUsername() {
        Assert.notNull(customUserDetails.getUsername(), "Cannot get username");
    }

    @Test
    void getEmail() {
        Assert.notNull(customUserDetails.getEmail(), "Cannot get email!");
    }

    @Test
    void getFullName() {
        Assert.notNull(customUserDetails.getFullName(), "Cannot get fullName!");
    }

    @Test
    void getDetails() {
        Assert.notNull(customUserDetails.getDetails(), "Cannot get details!");
    }

    @Test
    void isAccountNonExpired() {
        Assert.isTrue(customUserDetails.isAccountNonExpired(), "Account is expired!");
    }

    @Test
    void isAccountNonLocked() {
        Assert.isTrue(customUserDetails.isAccountNonLocked(), "Account is locked!");
    }

    @Test
    void isCredentialsNonExpired() {
        Assert.isTrue(customUserDetails.isCredentialsNonExpired(), "Credentials are expired!");
    }

    @Test
    void isEnabled() {
        Assert.isTrue(customUserDetails.isEnabled(), "Account is not enabled!");
    }

}