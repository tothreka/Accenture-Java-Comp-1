package com.rekadanilaci.accenture.dto;

import com.rekadanilaci.accenture.domain.Reservation;

import java.time.LocalDate;

public class ReservationListItem {
    private Long id;
    private String employeeName;
    private LocalDate day;
    private String status;

    public ReservationListItem(Reservation reservation) {
        this.id = reservation.getId();
        this.employeeName = reservation.getEmployee().getName();
        this.day = reservation.getDay();
        this.status = reservation.getReservationStatus().toString();
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getDay() {
        return day;
    }

    public String getStatus() {
        return status;
    }
}

