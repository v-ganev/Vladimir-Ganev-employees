package com.sirma.task.longestprojects.dao;

import com.sirma.task.longestprojects.domain.EmployeeProject;
import com.sirma.task.longestprojects.repository.EmployeeProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeProjectDAO implements DAO<EmployeeProject> {

    private final EmployeeProjectsRepository repository;

    public EmployeeProjectDAO(@Autowired EmployeeProjectsRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeProject save(EmployeeProject employeeProject) {
        return repository.save(employeeProject);
    }

    @Override
    public Optional<EmployeeProject> get(String id) {
        return Optional.empty();
    }

    @Override
    public Page<EmployeeProject> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public EmployeeProject update(EmployeeProject employee) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

}
