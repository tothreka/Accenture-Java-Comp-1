package com.rekadanilaci.accenture.controller;

import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.dto.EmployeeDataItem;
import com.rekadanilaci.accenture.dto.ReservationDataItem;
import com.rekadanilaci.accenture.dto.ReservationItem;
import com.rekadanilaci.accenture.dto.ReservationListItem;
import com.rekadanilaci.accenture.service.OfficeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office")
public class OfficeController {
    private OfficeManagementService officeService;

    @Autowired
    public OfficeController(OfficeManagementService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    public ResponseEntity addReservation(@RequestBody ReservationItem reservationItem) {
        officeService.addNewReservation(reservationItem);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //TODO ezt le kell cserélni
    @GetMapping("/{employeeId}")
    public ResponseEntity getStatus(@PathVariable Long employeeId) {
        String status = officeService.getReservationStatus(employeeId);
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @GetMapping("/reservations/{employeeId}")
    public ResponseEntity getReservationList(@PathVariable Long employeeId) {
        List<ReservationDataItem> reservationList = officeService.getReservationList(employeeId);
        return new ResponseEntity(reservationList, HttpStatus.OK);
    }

    @PutMapping("/enter/{reservationId}")
    public ResponseEntity requestEntry(@RequestBody ReservationDataItem reservationDataItem, @PathVariable Long reservationId) {
        boolean canEntry = officeService.requestEntryToOffice(reservationDataItem, reservationId);
        return canEntry ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/leave/{reservationId}")
    public ResponseEntity exitOffice(@RequestBody ReservationDataItem reservationDataItem, @PathVariable Long reservationId) {
        boolean exit = officeService.exitOffice(reservationDataItem, reservationId);
        return exit ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity updateReservationList(@RequestBody List<ReservationDataItem> resList, @PathVariable Long employeeId) {
        List<ReservationDataItem> reservationList = officeService.updateReservationList(employeeId, resList);
        return reservationList != null ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/check")
    public ResponseEntity checkIfReservationExists(@RequestBody ReservationItem reservationItemToCheck) {
        boolean exists = officeService.checkIfReservationExists(reservationItemToCheck);
        return new ResponseEntity(exists, HttpStatus.OK);
    }

    @PutMapping("/employee/update/{employeeId}")
    public ResponseEntity updateEmployeeData(@RequestBody EmployeeDataItem employeeDataItem, @PathVariable Long employeeId) {
        Employee employee = officeService.updateEmployee(employeeDataItem, employeeId);
        return employee != null ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/today")
    public ResponseEntity getReservationsForToday() {
        List<ReservationDataItem> enrolledReservationsForToday = officeService.getReservationsForToday();
        return new ResponseEntity(enrolledReservationsForToday, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAllReservation() {
        List<ReservationListItem> all = officeService.getAllReservationFromDb();
        return new ResponseEntity(all, HttpStatus.OK);
    }
}
