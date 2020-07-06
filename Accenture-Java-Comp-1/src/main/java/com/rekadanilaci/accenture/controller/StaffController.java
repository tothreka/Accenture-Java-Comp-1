package com.rekadanilaci.accenture.controller;

import com.rekadanilaci.accenture.dto.AdminLoginModel;
import com.rekadanilaci.accenture.service.OfficeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity verifyAdminFromDb(@RequestBody AdminLoginModel adminLoginModel) {
        boolean exists = officeService.getAdminById(adminLoginModel.getId()) != null;

        return new ResponseEntity(exists ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
