package com.rekadanilaci.accenture.controller;

import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.service.OfficeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/office")
public class OfficeController {
    private OfficeManagementService officeService;

    @Autowired
    public OfficeController(OfficeManagementService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    public ResponseEntity addReservation(@RequestBody ReservationDto reservationDto) {
        officeService.createNewReservation(reservationDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity getStatus(@PathVariable Long employeeId) {
        String status = officeService.getReservationStatus(employeeId);
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @GetMapping("/entry/{employeeId}")
    public ResponseEntity requestEntry(@PathVariable Long employeeId) {
        boolean canEntry = officeService.requestEntryToOffice(employeeId);
        return new ResponseEntity(canEntry, HttpStatus.OK);
    }

    @GetMapping("/exit/{employeeId}")
    public ResponseEntity exitOffice(@PathVariable Long employeeId) {
        boolean exit = officeService.exitOffice(employeeId);
        return new ResponseEntity(exit, HttpStatus.OK);
    }
}
