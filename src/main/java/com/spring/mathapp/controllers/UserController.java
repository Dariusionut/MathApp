package com.spring.mathapp.controllers;

import com.spring.mathapp.models.User;
import com.spring.mathapp.services.CountryService;
import com.spring.mathapp.services.RoleService;
import com.spring.mathapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    CountryService countryService;

    @GetMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("title", "Users");

        return "user/user_list";
    }

    @GetMapping(value = "/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("title", "Register User");

        return "user/user_form";
    }

    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasAnyRole('EDITOR', 'ADMIN')")
    public String getUserEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("title", "Edit User");

        return "user/user_form";
    }

    @GetMapping(value = "/update/{userName}")
    @PreAuthorize("#userName == principal.username")
    public String editUser(@PathVariable("userName") String userName, Model model) {
        model.addAttribute("user", userService.findByUsername(userName));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("title", "Edit User");

        return "user/user_form";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/users";
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return "redirect:/users";
    }

    @GetMapping(value = "/deleteAll")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAllUsers() {
        userService.deleteAll(userService.findAll());

        return "redirect:/users";
    }

    @GetMapping(value = "/search" )
    @PreAuthorize("isAuthenticated()")
    public String search(@RequestParam("userName") String name, Model model) {
        model.addAttribute("userList", userService.searchBy(name));
        return "/user/user_list";
    }

    @GetMapping(value = "/profile/{userName}")
    @PreAuthorize("#userName == principal.username")
    public String getUserProfile(@PathVariable("userName") String userName, Model model) {
        model.addAttribute("user", userService.findByUsername(userName));
        model.addAttribute("title", "Profile");

        return "user/user_profile";
    }

}
