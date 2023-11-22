package com.sirma.task.longestprojects.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DAO<T> {

    T save(T t);

    Optional<T> get(String id);

    Page<T> getAll(Pageable pageable);

    T update(T employee);

    void delete(String id);

    long count();

    void deleteAll();
}
