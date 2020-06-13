package com.rekadanilaci.accenture.controller;

import com.rekadanilaci.accenture.service.OfficeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private OfficeManagementService officeService;

    @Autowired
    public EmployeeController(OfficeManagementService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    public ResponseEntity fillDbWithEmployees() {
        officeService.fillEmployees();
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
