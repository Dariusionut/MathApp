package com.spring.mathapp.repositories;

import com.spring.mathapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);

    List<Role> findByNameContainsOrNameContainingAllIgnoreCase(String name, String aName);
}
