package com.rekadanilaci.accenture.service;

import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.domain.Office;
import com.rekadanilaci.accenture.domain.Reservation;
import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.repository.EmployeeRepository;
import com.rekadanilaci.accenture.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

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

    private void saveReservationToDatabase(Employee employee, LocalDate day) {
        Reservation newReservation = new Reservation(employee, day);
        reservationRepository.save(newReservation);
    }
}
