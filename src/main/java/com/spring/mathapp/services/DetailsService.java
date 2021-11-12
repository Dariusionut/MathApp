package com.spring.mathapp.services;

import com.spring.mathapp.exceptions.UserDetailsNotFoundException;
import com.spring.mathapp.models.Details;
import com.spring.mathapp.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailsService extends MyService<Details, UserDetailsRepository> {


    @Override
    public void save(Details details) {

        details.setAge(details.getAge());

        super.save(details);
    }

    @Override
    public Details findById(Long id) throws UserDetailsNotFoundException {
        return super.getRepository().findById(id).orElseThrow(() -> new UserDetailsNotFoundException("User details not found!"));
    }

    @Override
    public void deleteById(Long id) throws UserDetailsNotFoundException {
        Optional<Details> userDetailsOptional = super.getRepository().findById(id);
        if (userDetailsOptional.isEmpty()){
            throw new UserDetailsNotFoundException("User Details not found!");
        }
        super.getRepository().deleteById(id);

    }
}
