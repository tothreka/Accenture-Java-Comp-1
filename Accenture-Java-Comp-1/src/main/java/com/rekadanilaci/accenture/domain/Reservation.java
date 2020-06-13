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

    public DailyList getDailyList() {
        return dailyList;
    }
    //================== SETTERS =================


    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void setDailyList(DailyList dailyList) {
        this.dailyList = dailyList;
    }
}
