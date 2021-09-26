package com.spring.mathapp.services;

import com.spring.mathapp.exceptions.MyRoleNotFoundException;
import com.spring.mathapp.models.Role;
import com.spring.mathapp.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends MyService<Role, RoleRepository> {

    @Override
    public void save(Role role) {
        role.setName(role.getName().toUpperCase());

        super.save(role);
    }

    @Override
    public Role findById(Long id) {

        return super.getRepository().findById(id).orElseThrow(() -> new MyRoleNotFoundException("Role not found!"));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Role> roleOptional = super.getRepository().findById(id);
        if (roleOptional.isEmpty()) {
            throw new MyRoleNotFoundException("Role not found!");
        }
        super.deleteById(id);
    }

    public void setDefaultRoles() {
        Role roleUser, roleEditor, roleAdmin;
        roleUser = new Role("uSeR");
        roleEditor = new Role("EDITOR");
        roleAdmin = new Role("ADMIN");

        List<Role> roles = List.of(roleUser, roleEditor, roleAdmin);

        for (Role role : roles) {
            role.setName(role.getName().toUpperCase());
            Optional<Role> roleOptional = Optional.ofNullable(super.getRepository().findRoleByName(role.getName()));
            if (roleOptional.isEmpty()) {
                super.save(role);
            }
        }
    }

    public Role findRoleByName(String roleName) {
        return super.getRepository().findRoleByName(roleName);
    }

}
