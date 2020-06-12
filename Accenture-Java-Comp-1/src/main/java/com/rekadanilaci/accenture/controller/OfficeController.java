package com.rekadanilaci.accenture.controller;

import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/office"))
public class OfficeController {
    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    public ResponseEntity addReservation(@RequestBody ReservationDto reservationDto) {
        officeService.createReservation(reservationDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/status")
    public ResponseEntity getStatus(@PathVariable Long employeeId) {
        String status = officeService.requestStatus(employeeId);
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @GetMapping("/entryrequest")
    public ResponseEntity requestEntry(@PathVariable Long employeeId) {
        boolean canEntry = officeService.checkEntryRequest(employeeId);
        return new ResponseEntity(canEntry, HttpStatus.OK);
    }

    @PutMapping("/{id}/")
    public ResponseEntity exitOffice(@PathVariable Long id) {
        officeService.exitOffice(id);
        return new ResponseEntity(HttpStatus.valueOf("EXIT"));
    }
}
