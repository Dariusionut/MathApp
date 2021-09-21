package com.spring.mathapp.controllers;

import org.springframework.ui.Model;

public interface IController<T> {

    String getAll(Model model);

    String getForm(Model model);

    String getEditForm(Long id, Model model);

    String save(T entity);

    String deleteById(Long id);
}
