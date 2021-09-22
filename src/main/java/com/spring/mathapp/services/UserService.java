package com.spring.mathapp.services;

import com.spring.mathapp.exceptions.UserNotFoundException;
import com.spring.mathapp.models.Role;
import com.spring.mathapp.models.User;
import com.spring.mathapp.repositories.UserDetailsRepository;
import com.spring.mathapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService extends MyService<User, UserRepository> {

    @Autowired
    UserDetailsRepository detailsRepository;
    @Autowired
    RoleService roleService;

    @Override
    public User findById(Long id) {
        return super.getRepository().findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public User findByUsername(String userName) {
        return super.getRepository().findUserByUserName(userName);
    }

    @Override
    public void save(User user) {
        user.setEncodedPassword(user.getPassword());
        user.getFullName();
        user.setAge(user.getAge());

        if (user.getIsEnabled() == null) {
            user.setIsEnabled(true);
        }

        if (user.getDetails() != null) {
            user.addDetails(user.getDetails().getInfo());
        } else {
            user.addDetails("Details");
        }

        roleService.setDefaultRoles();

        if (user.getRoles() == null) {
            Role userRole = roleService.findRoleByName("USER");
            if (userRole != null) {
                user.setRoles(Set.of(userRole));
            }
        }

        super.save(user);
    }

    @Override
    public void saveAll(List<User> users) {

        for (User user : users) {
            user.setEncodedPassword(user.getPassword());
            user.setAge(user.getAge());
            if (user.getRoles() == null) {
                Role userRole = roleService.findRoleByName("USER");
                user.setRoles(Set.of(userRole));
            }

            if (user.getDetails() == null) {
                user.addDetails("Please fill up your details!");
            }

            if (user.getIsEnabled() == null) {
                user.setIsEnabled(true);
            }
        }

        super.saveAll(users);
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> userOptional = super.getRepository().findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        super.deleteById(id);
    }

    @Override
    public void deleteAll(List<User> users) {

        super.deleteAll(users);
    }

    public User getUserByEmail(String email) {
        return super.getRepository().findUserByEmail(email);
    }

    public List<User> searchBy(String name) {
        List<User> results;
        if (name != null && (name.trim().length() > 0)) {
            results = super.getRepository().findUserByUserNameContainsOrFirstNameContainsOrLastNameContaining(name, name, name);
        } else {
            results = findAll();
        }
        return results;
    }
}
