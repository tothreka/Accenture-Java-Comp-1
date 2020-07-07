package com.rekadanilaci.accenture.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rekadanilaci.accenture.domain.Reservation;

import java.time.LocalDate;

import static com.rekadanilaci.accenture.AccentureJavaComp1Application.DATE_FORMAT;

public class ReservationDataItem {
    private Long id;
    private Long employee;

    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate day;
    private String reservationStatus;

    public ReservationDataItem(Reservation reservation) {
        this.id = reservation.getId();
        this.employee = reservation.getEmployee().getId();
        this.day = reservation.getDay();
        this.reservationStatus = reservation.getReservationStatus().toString();
    }

    public ReservationDataItem() {
    }

    public Long getId() {
        return id;
    }

    public Long getEmployee() {
        return employee;
    }

    public LocalDate getDay() {
        return day;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }
}
