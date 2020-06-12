package com.rekadanilaci.accenture.domain;

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

    public Reservation(Employee person, LocalDate day) {
        this.person = person;
        this.day = day;
        this.active = ReservationStatus.ENROLLED;
    }
    //TODO a serviceben kell megcsinálni a logikát blablabla


}
