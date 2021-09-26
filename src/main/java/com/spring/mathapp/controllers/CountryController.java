package com.spring.mathapp.controllers;

import com.spring.mathapp.models.Country;
import com.spring.mathapp.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/country")
@PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
public class CountryController implements IController<Country> {

    @Autowired
    CountryService countryService;

    @Override
    @GetMapping(value = "/all")
    public String getAll(Model model) {
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("title", "countries");
        return "country/country_list";
    }

    @Override
    @GetMapping(value = "/add")
    public String getForm(Model model) {
        model.addAttribute("country", new Country());
        model.addAttribute("title", "country form");

        return "country/country_form";
    }

    @Override
    @GetMapping(value = "edit/{id}")
    public String getEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("country", countryService.findById(id));

        return "country/country_form";
    }

    @Override
    @PostMapping(value = "/save")
    public String save(Country country) {
        countryService.save(country);
        return "redirect:/country/all";
    }

    @Override
    @GetMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        countryService.deleteById(id);
        return "redirect:/country/all";
    }

    @GetMapping(value = "deleteAll")
    public String deleteAll(){
        countryService.deleteAll(countryService.findAll());

        return "redirect:/country/all";
    }

}
