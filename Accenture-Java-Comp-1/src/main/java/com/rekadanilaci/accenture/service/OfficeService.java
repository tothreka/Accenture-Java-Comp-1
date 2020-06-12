package com.rekadanilaci.accenture.service;

import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.domain.Office;
import com.rekadanilaci.accenture.domain.Reservation;
import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.repository.EmployeeRepository;
import com.rekadanilaci.accenture.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class OfficeService {
    private EmployeeRepository employeeRepository;
    private ReservationRepository reservationRepository;
    private final Office office;


    @Autowired
    public OfficeService(EmployeeRepository employeeRepository, ReservationRepository reservationRepository, Office office) {
        this.employeeRepository = employeeRepository;
        this.reservationRepository = reservationRepository;
        this.office = office;
    }

    public void createReservation(ReservationDto reservationDto) {
        Long employeeId = reservationDto.getPerson();
        Employee employee = employeeRepository.getOne(employeeId);
        LocalDate day = reservationDto.getDay();
        saveReservationToDatabase(employee, day);
        office.registerReservation(employeeId, day);
    }


    public String requestStatus(Long employeeId) {
        String status = office.reportStatus(employeeId, LocalDate.now());
        return status;
    }


    public boolean checkEntryRequest(Long employeeId) {
        boolean canEntry = office.requestEntry(employeeId);
        return canEntry;
    }

    // =========== GENERAL METHODS ===========

    private void saveReservationToDatabase(Employee employee, LocalDate day) {
        Reservation newReservation = new Reservation(employee, day);
        reservationRepository.save(newReservation);
    }
}
