package com.sirma.task.longestprojects.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePairDTO {

    long employee1ID;

    long employee2ID;

    long projectID;

    long totalDaysWorked;
}
