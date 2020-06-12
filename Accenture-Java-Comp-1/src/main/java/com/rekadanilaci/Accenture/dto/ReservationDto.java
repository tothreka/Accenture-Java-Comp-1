package com.rekadanilaci.Accenture.dto;

import java.time.LocalDate;

public class ReservationDto {
    private Long person;
    private LocalDate day;
    private String status;

    public ReservationDto(Long person, LocalDate day, String status) {
        this.person = person;
        this.day = day;
        this.status = status;
    }

    public Long getPerson() {
        return person;
    }

    public LocalDate getDay() {
        return day;
    }

    public String getStatus() {
        return status;
    }
}
