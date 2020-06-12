package com.rekadanilaci.Accenture.domain;

import com.rekadanilaci.Accenture.dto.ReservationDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany
    @Column(name = "employee")
    private Employee person;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "status")
    private ReservationStatus active;

    public Reservation(ReservationDto reservationDto) {
        this.person = reservationDto.getPerson();
        this.day = reservationDto.getDay();
        this.active = reservationDto.getStatus();
    }
}
