package com.testtask.services;

import java.util.List;

public interface SimpleService<T> {
    T save(T t);
    T getById(Long id);
    List<T> getAll();
    void delete(Long id);
}
