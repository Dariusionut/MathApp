package com.spring.mathapp.configuration;

import com.spring.mathapp.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class CountryConfigTest {

    boolean countryRo;
    boolean countryUk;

    @Autowired
    CountryService countryService;

    @BeforeEach
    void setUp() {
        countryRo = countryService.findCountryByName("Romania") != null;
        countryUk = countryService.findCountryByName("United Kingdom") != null;
    }

    @Test
    void countryRunner() {
        assertTrue(countryRo && countryUk, "CountryRunner does not work!");
    }
}