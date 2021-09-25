package com.spring.mathapp.services;

import com.spring.mathapp.models.Country;
import com.spring.mathapp.models.Role;
import com.spring.mathapp.models.User;
import com.spring.mathapp.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService extends MyService<Country, CountryRepository> {

    public Country findCountryByName(String name) {
        return super.getRepository().findCountryByName(name);
    }

    public List<Country> searchBy(String name) {
        List<Country> results;
        if (name != null && (name.trim().length() > 0)) {
            results = super.getRepository().findCountryByNameContainsOrDescriptionContaining(name, name);
        } else {
            results = findAll();
        }
        return results;
    }

    public void setDefaultCountries() {
        Country romania, uk;

        romania = new Country("Romania", "Ro country");
        uk = new Country("United Kingdom", "U.K. country");

        List<Country> countries = List.of(romania, uk);

        for (Country country : countries) {
            Optional<Country> countryOptional = Optional.ofNullable(super.getRepository().findCountryByName(country.getName()));
            if (countryOptional.isEmpty()) {
                super.save(country);
            }
        }
    }
}
