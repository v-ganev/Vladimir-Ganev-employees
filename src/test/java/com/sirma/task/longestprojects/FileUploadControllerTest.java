package com.sirma.task.longestprojects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.sirma.task.longestprojects.util.TestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LongestProjectsApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUploadFileAndCheckHtmlResponseForOneCommonDay() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/employee-pairs/longest-pairs")
                .file(getFileUpload(getCsvDataTest1()))
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(xpath("//td[1]").number(EMPLOYEE1_ID))
                .andExpect(xpath("//td[2]").number(EMPLOYEE2_ID))
                .andExpect(xpath("//td[3]").number(PROJECT_ID))
                .andExpect(xpath("//td[4]").number(TEST1_DAYS_WORKED))
                .andReturn();

    }

    @Test
    void testUploadFileAndCheckHtmlResponseForTwoCommonDays() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/employee-pairs/longest-pairs")
                        .file(getFileUpload(getCsvDataTest2()))
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(xpath("//td[1]").number(EMPLOYEE1_ID))
                .andExpect(xpath("//td[2]").number(EMPLOYEE2_ID))
                .andExpect(xpath("//td[3]").number(PROJECT_ID))
                .andExpect(xpath("//td[4]").number(TEST2_DAYS_WORKED))
                .andReturn();

    }

    @Test
    void testUploadFileAndCheckHtmlResponseForTenCommonDays() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/employee-pairs/longest-pairs")
                        .file(getFileUpload(getCsvDataTest3()))
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(xpath("//td[1]").number(EMPLOYEE1_ID))
                .andExpect(xpath("//td[2]").number(EMPLOYEE2_ID))
                .andExpect(xpath("//td[3]").number(PROJECT_ID))
                .andExpect(xpath("//td[4]").number(TEST3_DAYS_WORKED))
                .andReturn();

    }

    @Test
    void testUploadFileAndCheckHtmlResponseCommonDaysUpToToday() throws Exception {
        LocalDate latestFromDate = LocalDate.of(2019, 1, 7);
        LocalDate today = LocalDate.now();
        long expectedCommonDays = ChronoUnit.DAYS.between(latestFromDate, today) + 1 /*including Today*/;

        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/employee-pairs/longest-pairs")
                        .file(getFileUpload(getCsvDataTest4()))
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(xpath("//td[1]").number(EMPLOYEE1_ID))
                .andExpect(xpath("//td[2]").number(EMPLOYEE2_ID))
                .andExpect(xpath("//td[3]").number(PROJECT_ID))
                .andExpect(xpath("//td[4]").number(Double.valueOf(expectedCommonDays)))
                .andReturn();

    }

    private String getCsvDataTest1() {
        return  "1, 12, 2019-01-08, 2019-01-08\n" +
                "4, 12, 2009-01-01, 2019-01-08\n" +
                "2, 13, 2013-11-02, 2013-11-03\n" +
                "3, 11, 2013-11-02, 2013-11-05\n";
    }

    private String getCsvDataTest2() {
        return  "1, 12, 2019-01-07, 2019-01-08\n" +
                "4, 12, 2009-01-01, 2019-01-08\n";
    }

    private String getCsvDataTest3() {
        return  "1, 12, 2019-01-01, 2019-01-10\n" +
                "4, 12, 2009-01-01, 2019-01-11\n" +
                "2, 11, 2013-11-02, 2013-11-03\n" +
                "3, 11, 2013-11-02, 2013-11-05\n";
    }

    private String getCsvDataTest4() {
        return  "1, 12, 2019-01-07, NULL\n" +
                "4, 12, 2019-01-01, NULL\n";
    }

    private MockMultipartFile getFileUpload(String csvData) {
        return new MockMultipartFile(
                "file",
                "test.csv",
                "text/csv",
                csvData.getBytes()
        );
    }
}
