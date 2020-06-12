package com.rekadanilaci.accenture.dto;

import java.time.LocalDate;

public class ReservationDto {
    private Long person;
    private LocalDate day;

    public ReservationDto(Long person, LocalDate day) {
        this.person = person;
        this.day = day;
    }

    public Long getPerson() {
        return person;
    }

    public LocalDate getDay() {
        return day;
    }

}
