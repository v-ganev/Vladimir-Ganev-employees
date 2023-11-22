package com.sirma.task.longestprojects;

import com.sirma.task.longestprojects.dao.EmployeeProjectDAO;
import com.sirma.task.longestprojects.service.EmployeeProjectsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class EmployeeProjectServiceTest {

    @Autowired
    private EmployeeProjectsService employeeProjectsService;

    @Autowired
    private EmployeeProjectDAO employeeProjectDAO;

    @Test
    public void testProcessCSV() {
        MultipartFile multipartFile = getFileUpload();
        employeeProjectsService.processData(multipartFile);

        long count = employeeProjectDAO.count();
        assertEquals(2, count);
    }

    static MockMultipartFile getFileUpload() {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.csv",
                "text/csv",
                ("2, 13, 2013-11-02, 2013-11-03\n" +
                 "1, 13, 2013-11-02, 2014-11-03\n").getBytes()
        );
        return file;
    }
}
