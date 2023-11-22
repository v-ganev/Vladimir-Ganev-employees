package com.sirma.task.longestprojects.dao;

import com.sirma.task.longestprojects.domain.EmployeePair;
import com.sirma.task.longestprojects.repository.EmployeePairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeePairDAO implements DAO<EmployeePair> {

    private final EmployeePairRepository repository;

    public EmployeePairDAO(@Autowired EmployeePairRepository repository) {
        this.repository = repository;
    }

    public List<Object[]> findLongestWorkingPairs() {
        return repository.findLongestWorkingPairs();
    }

    public List<EmployeePair> listLongestWorkingPairs(Long emp1ID, Long emp2ID) {
        return repository.listLongestWorkingPairs(emp1ID, emp2ID);
    }

    public void insertWorkingPairs() {
        repository.insertWorkingPairs();
    }

    @Override
    public EmployeePair save(EmployeePair employeeProject) {
        return repository.save(employeeProject);
    }

    @Override
    public Optional<EmployeePair> get(String id) {
        return Optional.empty();
    }

    @Override
    public Page<EmployeePair> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public EmployeePair update(EmployeePair employee) {
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
