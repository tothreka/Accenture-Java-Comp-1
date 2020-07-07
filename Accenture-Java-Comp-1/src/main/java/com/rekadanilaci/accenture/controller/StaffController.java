package com.rekadanilaci.accenture.controller;

import com.rekadanilaci.accenture.dto.*;
import com.rekadanilaci.accenture.service.OfficeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private OfficeManagementService officeService;

    @Autowired
    public StaffController(OfficeManagementService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    public ResponseEntity fillDbWithEmployees() {
        officeService.fillEmployees();
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/admin/login")
    public ResponseEntity verifyAdminFromDb(@RequestBody AdminLoginItem adminLoginItem) {
        boolean exists = officeService.getAdminForLoginById(adminLoginItem.getId()) != null;

        return new ResponseEntity(exists ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity fetchAdminData(@PathVariable Long id) {
        AdminDataItem adminById = officeService.fetchAdminData(id);
        return new ResponseEntity(adminById, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity registerNewEmployee(@RequestBody EmployeeRegistrationItem employeeRegistrationItem) {
        EmployeeDataItem employeeDataItem = officeService.addNewEmployee(employeeRegistrationItem);
        return new ResponseEntity(employeeDataItem, HttpStatus.CREATED);
    }

    @PostMapping("/employee/login")
    public ResponseEntity verifyEMployeeFromDb(@RequestBody EmployeeLoginItem employeeLoginItem) {
        boolean exists = officeService.getEmployeeForLoginById(employeeLoginItem.getId()) != null;

        return new ResponseEntity(exists ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity fetchEmployeeData(@PathVariable Long id) {
        EmployeeDataItem employeeDataItem = officeService.fetchEmployeeData(id);
        return new ResponseEntity(employeeDataItem, HttpStatus.OK);
    }
}
