package com.spring.mathapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MainController {

    @GetMapping
    @PreAuthorize("permitAll()")
    public String mainView(Model model){
        model.addAttribute("title", "Welcome");
        model.addAttribute("welcome", "Welcome to Math App");
        model.addAttribute("welcomeUser", "How are you doing today?");
        return "index";
    }

    @GetMapping("/login")
    public String getLoginView(Model model){
        model.addAttribute("title", "Login");

        return "user/user_login";
    }
}
