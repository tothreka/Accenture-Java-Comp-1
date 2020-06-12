package com.rekadanilaci.accenture.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
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

    @Column(name = "reservationStatus")
    private ReservationStatus reservationStatus;

    public Reservation(Employee employee, LocalDate day) {
        this.employee = employee;
        this.day = day;
        this.reservationStatus = ReservationStatus.ENROLLED;
    }


    // ======================= GETTERS =====================

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getDay() {
        return day;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    //================== SETTERS =================


    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
