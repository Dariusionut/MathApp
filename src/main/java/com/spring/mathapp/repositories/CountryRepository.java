package com.spring.mathapp.repositories;

import com.spring.mathapp.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findCountryByName(String name);

    List<Country> findCountryByNameContainsOrDescriptionContaining(String name, String description);
}
