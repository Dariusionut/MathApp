package com.spring.mathapp.services;

import java.util.List;

public interface IService<T>{

    List<T> findAll();

    T findById(Long id);

    void save(T entity);

    void saveAll(List<T> list);

    void deleteById(Long id);

    void deleteAll(List<T> list);
}
