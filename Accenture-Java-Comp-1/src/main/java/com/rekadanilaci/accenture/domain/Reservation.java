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

    @ManyToOne
    @Column(name = "employee")
    private Employee employee;

    @Column(name = "day")
    @ManyToOne
    private LocalDate day;

    @Column(name = "status")
    private ReservationStatus active;

    public Reservation(Employee employee, LocalDate day) {
        this.employee = employee;
        this.day = day;
        this.active = ReservationStatus.ENROLLED;
    }
    //TODO a serviceben kell megcsinálni a logikát blablabla


    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getDay() {
        return day;
    }

    public ReservationStatus getActive() {
        return active;
    }
}
