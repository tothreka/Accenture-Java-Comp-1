package com.rekadanilaci.accenture.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dailyList")
public class DailyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reservationList")
    @OneToMany(mappedBy = "day")
    private List<Reservation> reservationList;

    public DailyList() {
    }

    public int getPositionInEnrollList(Long employeeId, int officeFreePlaces) {
        int enrolledEmployees = 0;
        int enteredEmployees = 0;
        for (Reservation reservation : reservationList) {
            if (reservation.getEmployee().getId() == employeeId) {
                break;
            } else if (reservation.getReservationStatus().equals(ReservationStatus.ENROLLED)) {
                enrolledEmployees++;
            } else if (reservation.getReservationStatus().equals(ReservationStatus.ENTERED_OFFICE)) {
                enteredEmployees++;
            }
        }
        int position = enrolledEmployees - (officeFreePlaces - enteredEmployees);
        return position;
    }

    public void addReservation(Reservation reservation) {
        // TODO ellenőrizni, nincs e már aktív foglalása erre a napra
        this.reservationList.add(reservation);
    }


    //========================= GETTERS =========================

    public Long getId() {
        return id;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
}
