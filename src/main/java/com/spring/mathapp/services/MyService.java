package com.spring.mathapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public abstract class MyService<T, R extends JpaRepository<T, Long>> implements IService<T> {

    @Autowired
    private R repository;

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T findById(Long id) {
        return getRepository().findById(id).orElseThrow(() -> new IllegalArgumentException("Not found!"));
    }

    @Override
    public void save(T entity) {
        getRepository().save(entity);
    }

    @Override
    public void deleteById(Long id) {
        Optional<T> optional = getRepository().findById(id);
        if (optional.isEmpty()) {
            throw new IllegalStateException("Not found!");
        }
        getRepository().deleteById(id);
    }

    public R getRepository() {
        return repository;
    }

}
