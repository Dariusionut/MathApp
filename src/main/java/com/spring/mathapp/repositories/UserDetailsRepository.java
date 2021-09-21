package com.spring.mathapp.repositories;

import com.spring.mathapp.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<Details, Long> {
}
