package com.rekadanilaci.accenture.domain;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Reservations can be created by Employees only. Nobody can enter
 * the office in the pandemic period without a valid reservation.
 *
 * Reservations always specify the day, on which the employee wants to
 * visit the office. Reservations can not contain specific hours.
 * If a reservation can not be fulfilled on the given day, a next
 * reservation must be created by the employee for the next day.
 *
 * Reservations can be created for the actual day and all dates after
 * the actual day. No retrospective reservation is allowed.
 *
 * The application does not manage business hours, we assumed that entry
 * is allowed in all hours of the day.
 *
 * Reservations have always a status, see ReservationStatus enum class
 * for more information.
 */

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Employee employee;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "reservationStatus")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne
    @JoinColumn(name = "dailyList.id")
    private DailyList dailyList;

    public Reservation(Employee employee, LocalDate day) {
        this.employee = employee;
        this.day = day;
        this.reservationStatus = ReservationStatus.ENROLLED;
    }

    public Reservation() {
    }




    // ===================== GETTERS =======================

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public LocalDate getDay() {
        return day;
    }

//====================== SETTERS ========================

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void setDailyList(DailyList dailyList) {
        this.dailyList = dailyList;
    }
}
