package com.sirma.task.longestprojects.controller;

import com.sirma.task.longestprojects.dto.EmployeePairDTO;
import com.sirma.task.longestprojects.service.EmployeeProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/v1/employee-pairs")
public class EmployeeProjectsController {

    private final EmployeeProjectsService employeeProjectsService;

    public EmployeeProjectsController(@Autowired EmployeeProjectsService employeeProjectsService) {
        this.employeeProjectsService = employeeProjectsService;
    }

    @GetMapping(value = {"", "/"})
    public String index() {
        return "upload-form";
    }

    @PostMapping("/longest-pairs")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        List<EmployeePairDTO> dataList = employeeProjectsService.processData(file);

        model.addAttribute("dataList", dataList);
        return "data-grid";
    }
}
