package com.rekadanilaci.accenture.dto;

import java.time.LocalDate;

public class ReservationItem {
    private Long person;
    private LocalDate day;

    public ReservationItem(Long person, LocalDate day) {
        this.person = person;
        this.day = day;
    }

    public ReservationItem() {
    }

    //============================== GETTERS ==============================

    public Long getPerson() {
        return person;
    }

    public LocalDate getDay() {
        return day;
    }

}
