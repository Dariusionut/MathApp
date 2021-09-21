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
        if (role.getName() == role.getName().toLowerCase()){
            role.setName(role.getName().toUpperCase());
        }

        super.save(role);
    }

    @Override
    public Role findById(Long id) {
        return super.getRepository().findById(id).orElseThrow(()-> new MyRoleNotFoundException("Role Not Found!"));
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
        roleUser = new Role("USER");
        roleEditor = new Role("EDITOR");
        roleAdmin = new Role("ADMIN");

        Optional<Role> userRole = Optional.ofNullable(super.getRepository().findRoleByName("USER"));
        Optional<Role> editorRole = Optional.ofNullable(super.getRepository().findRoleByName("EDITOR"));
        Optional<Role> adminRole = Optional.ofNullable(super.getRepository().findRoleByName("ADMIN"));

        if (userRole.isEmpty()){
            super.save(roleUser);
        }

        if (editorRole.isEmpty()){
            super.save(roleEditor);
        }

        if (adminRole.isEmpty()){
            super.save(roleAdmin);
        }

    }

    public Role findRoleByName(String roleName){
        return super.getRepository().findRoleByName(roleName);
    }

    public List<Role> searchBy(String name){
        List<Role> results;
        if (name != null && name.trim().length() > 0) {
            results = super.getRepository().findByNameContainsOrNameContainingAllIgnoreCase(name, name);
        } else {
            results = super.findAll();
        }
        return results;
    }
}
