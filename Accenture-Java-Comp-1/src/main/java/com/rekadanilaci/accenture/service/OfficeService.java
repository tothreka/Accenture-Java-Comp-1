package com.rekadanilaci.accenture.service;

import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.domain.Reservation;
import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class OfficeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public OfficeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createReservation(ReservationDto reservationDto) {
        Employee employee = employeeRepository.getOne(reservationDto.getPerson());
        LocalDate day = reservationDto.getDay();
        Reservation newReservation = new Reservation(employee, day);
    }
}
