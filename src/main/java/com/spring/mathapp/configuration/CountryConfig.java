package com.spring.mathapp.configuration;

import com.spring.mathapp.services.CountryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountryConfig {

    @Bean
    CommandLineRunner countryRunner(CountryService countryService) {
        return args -> {

            countryService.setDefaultCountries();

        };
    }
}
