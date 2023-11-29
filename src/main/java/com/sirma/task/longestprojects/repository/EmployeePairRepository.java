package com.sirma.task.longestprojects.repository;

import com.sirma.task.longestprojects.domain.EmployeePair;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeePairRepository extends JpaRepository<EmployeePair, Long> {

    @Modifying
    @Transactional
    @Query("INSERT INTO EmployeePair (employee1ID, employee2ID, projectID, totalDaysWorked) " +
            "SELECT DISTINCT ep1.employeeID, ep2.employeeID, ep1.projectID, " +
            "ABS(DATEDIFF(day, LEAST(IFNULL(ep1.dateTo, CURRENT_DATE), IFNULL(ep2.dateTo, CURRENT_DATE)), GREATEST(ep1.dateFrom, ep2.dateFrom)))+1 " +
            "FROM EmployeeProject ep1 " +
            "JOIN EmployeeProject ep2 ON ep1.projectID = ep2.projectID AND ep1.employeeID < ep2.employeeID " +
            "WHERE GREATEST(ep1.dateFrom, ep2.dateFrom) <= LEAST(IFNULL(ep1.dateTo, CURRENT_DATE), IFNULL(ep2.dateTo, CURRENT_DATE))"
    )
    void insertWorkingPairs();

    @Query("SELECT employee1ID, employee2ID, SUM(totalDaysWorked) AS sumDaysWorked " +
            "FROM EmployeePair " +
            "GROUP BY employee1ID, employee2ID " +
            "ORDER BY sumDaysWorked DESC"
    )
    List<Object[]> findLongestWorkingPairs();

    @Query("SELECT ep FROM EmployeePair ep " +
            "WHERE (ep.employee1ID = :emp1ID AND ep.employee2ID = :emp2ID) " +
            "OR (ep.employee1ID = :emp2ID AND ep.employee2ID = :emp1ID)"
    )
    List<EmployeePair> listLongestWorkingPairs(Long emp1ID, Long emp2ID);
}