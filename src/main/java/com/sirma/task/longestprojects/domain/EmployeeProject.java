package com.sirma.task.longestprojects.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class EmployeeProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employee ID is mandatory.")
    private Long employeeID;

    @NotNull(message = "Project ID is mandatory.")
    private Long projectID;

    @NotNull(message = "Date From is mandatory.")
    private LocalDate dateFrom;

    private LocalDate dateTo;

    public EmployeeProject(Long employeeID, Long projectID, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeID = employeeID;
        this.projectID = projectID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
