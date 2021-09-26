package com.spring.mathapp.security;

import com.spring.mathapp.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
public class PasswordEncoderTest {

    BCryptPasswordEncoder encoder;
    User user;


    @BeforeEach
    void setUp() {
        encoder = new BCryptPasswordEncoder();
        user = new User("test", "rawPassword", "test@test.com", "fTest", "lTest");

    }

    @Test
    void encodingPasswordTest() {
        String rawPassword = "passTest";
        String encodedPassword = encoder.encode(rawPassword);
        boolean encodeSucceeds = encoder.matches(rawPassword, encodedPassword);

        Assert.isTrue(encodeSucceeds, "Password not encoded!");
    }

    @Test
    void userPasswordEncodingTest() {
        String rawPassword = user.getPassword();
        user.setEncodedPassword(user.getPassword());
        String encodedPass = user.getPassword();
        boolean encodeSucceeds = encoder.matches(rawPassword, encodedPass);

        Assert.isTrue(encodeSucceeds, "Password not encoded!");
    }

}
