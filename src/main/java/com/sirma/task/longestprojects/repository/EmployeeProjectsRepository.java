package com.sirma.task.longestprojects.repository;

import com.sirma.task.longestprojects.domain.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectsRepository extends JpaRepository<EmployeeProject, Long> {
}
