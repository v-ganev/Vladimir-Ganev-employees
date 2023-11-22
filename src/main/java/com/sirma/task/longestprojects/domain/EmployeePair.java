package com.sirma.task.longestprojects.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Long employee1ID;
    private Long employee2ID;
    private Long projectID;

    private Long totalDaysWorked;

}
