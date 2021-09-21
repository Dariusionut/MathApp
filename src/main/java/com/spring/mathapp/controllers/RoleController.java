package com.spring.mathapp.controllers;

import com.spring.mathapp.models.Role;
import com.spring.mathapp.services.RoleService;
import com.spring.mathapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController implements IController<Role> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    @GetMapping(value = "")
    public String getAll(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "Roles");

        return "role/role_list";
    }

    @Override
    @GetMapping(value = "/new")
    public String getForm(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "Add Role");

        return "role/role_form";
    }

    @Override
    @GetMapping(value = "/edit/{id}")
    public String getEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "Edit Role");
        return "role/role_form";
    }

    @Override
    @PostMapping(value = "/save")
    public String save(@ModelAttribute("role") Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    @Override
    @GetMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        roleService.deleteById(id);

        return "redirect:/roles";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam("name") String name, Model model) {
        model.addAttribute("role", roleService.searchBy(name));

        return "role/role_list";
    }
}
