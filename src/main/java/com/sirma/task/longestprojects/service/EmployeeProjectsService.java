package com.sirma.task.longestprojects.service;

import com.sirma.task.longestprojects.dao.EmployeePairDAO;
import com.sirma.task.longestprojects.dao.EmployeeProjectDAO;
import com.sirma.task.longestprojects.domain.EmployeePair;
import com.sirma.task.longestprojects.domain.EmployeeProject;
import com.sirma.task.longestprojects.dto.EmployeePairDTO;
import com.sirma.task.longestprojects.mapper.EmployeePairMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



@Service
public class EmployeeProjectsService {

    private final EmployeeProjectDAO employeeProjectDAO;
    private final EmployeePairDAO employeePairDAO;
    private final EmployeePairMapper employeePairMapper;

    private final DateTimeFormatter dateTimeFormatter;


    private final Logger logger = LoggerFactory.getLogger(EmployeeProjectsService.class);


    public EmployeeProjectsService(@Autowired EmployeeProjectDAO employeeProjectDAO, @Autowired EmployeePairDAO employeePairDAO,
                                   @Autowired EmployeePairMapper employeePairMapper, @Autowired DateTimeFormatter dateTimeFormatter) {
        this.employeeProjectDAO = employeeProjectDAO;
        this.employeePairDAO = employeePairDAO;
        this.employeePairMapper = employeePairMapper;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public List<EmployeePairDTO> processData(MultipartFile file) {
        cleanDB();
        readAndStoreData(file);

        return findLongestWorkingPair();
    }
    private void readAndStoreData(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CSVParser csvParser = CSVFormat.DEFAULT.withIgnoreSurroundingSpaces().withIgnoreEmptyLines().parse(reader);
            List<CSVRecord> records = csvParser.getRecords();

            for (CSVRecord record : records) {
                Long employeeID = Long.parseLong(record.get(0));
                Long projectID = Long.parseLong(record.get(1));
                LocalDate dateFrom = parseDate(record.get(2));
                LocalDate dateTo = parseDate(record.get(3));

                employeeProjectDAO.save(new EmployeeProject(employeeID, projectID, dateFrom, dateTo));
            }

        } catch (IOException e) {
            logger.warn(e.getMessage());
        }

    }

    private LocalDate parseDate(String date) {
        if (date.equals("NULL")) {
            return null;
        }

        return LocalDate.parse(date, dateTimeFormatter);
    }

    private List<EmployeePairDTO> findLongestWorkingPair() {
        employeePairDAO.insertWorkingPairs();
        List<Object[]> allEmployeePairs = employeePairDAO.findLongestWorkingPairs();

        Object[] maxDaysWorked = allEmployeePairs.stream()
                .max(Comparator.comparingLong(arr -> (Long) arr[2]))
                .orElse(null);

        List<EmployeePair> employeePairs = new ArrayList<>();
        if (maxDaysWorked != null) {
            List<Object[]> maxPairs = allEmployeePairs.stream()
                    .filter(arr -> (long) (Long) arr[2] == (Long) maxDaysWorked[2])
                    .toList();

            for (Object[] pair : maxPairs) {
                employeePairs.addAll(
                        employeePairDAO.listLongestWorkingPairs((Long) pair[0], (Long) pair[1])
                );
            }
        }

        List<EmployeePairDTO> pairs = new ArrayList<>();
        for (EmployeePair employeePair : employeePairs) {
            pairs.add(
                employeePairMapper.updateDtoFromEmployee(employeePair, new EmployeePairDTO())
            );
        }

        return pairs;
    }

    private void cleanDB() {
        employeeProjectDAO.deleteAll();
        employeePairDAO.deleteAll();
    }

}
