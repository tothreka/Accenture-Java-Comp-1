package com.rekadanilaci.accenture.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Office class has a Map that contains instances of DailyList as values.
 * The keys are the requested days, when the employees want to enter the office.
 * The DailyLists contains all Reservations created for the given day. Because
 * Reservations are immediately ordered to a DailyList after their creations,
 * the DailyLists contain the Reservations in order pf creations.
 *
 * When a Reservation is used, it will remain in the appropriate DailyList,
 * only the status of the Reservation will be changed.
 *
 * The appropriate DailyList will be created by the application automatically
 * with the first created Reservation for the given day.
 *
 * DailyLists will be never deleted, allowing backtracking of the entries and
 * leaves of the office.
 */

@Component
@Entity
@Table(name = "dailyList")
public class DailyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "dailyList")
    private List<Reservation> reservationList = new ArrayList<>();


    public DailyList() {
    }

   /* public int getPositionInEnrollList(Long employeeId, int officeFreePlaces) {
        int enrolledEmployees = 0;
        int enteredEmployees = 0;
        for (Reservation reservation : reservationList) {
            if (reservation.getEmployee().getId().equals(employeeId)) {
                break;
            } else if (reservation.getReservationStatus().equals(ReservationStatus.ENROLLED)) {
                enrolledEmployees++;
            } else if (reservation.getReservationStatus().equals(ReservationStatus.ENTERED_OFFICE)) {
                enteredEmployees++;
            }
        }
        return enrolledEmployees - (officeFreePlaces - enteredEmployees);
    }

    public boolean addReservation(Reservation reservation) {
        boolean isCreated = true;
        for (Reservation currentReservation : reservationList) {
            if (currentReservation.getEmployee().equals(reservation.getEmployee()) &&
            !currentReservation.getReservationStatus().equals(ReservationStatus.LEFT_OFFICE)) {
                isCreated = false;
            }
        }
        if (isCreated) {
            reservationList.add(reservation);
        }
        return isCreated;
    }

    public void enterOffice(Long employeeId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getEmployee().getId().equals(employeeId) &&
                    reservation.getReservationStatus().equals(ReservationStatus.ENROLLED)) {
                reservation.setReservationStatus(ReservationStatus.ENTERED_OFFICE);
            }
        }
    }

    public void exitOffice(Long employeeId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getEmployee().getId().equals(employeeId) &&
                    reservation.getReservationStatus().equals(ReservationStatus.ENTERED_OFFICE)) {
                reservation.setReservationStatus(ReservationStatus.LEFT_OFFICE);
            }
        }
    }*/


    //========================= GETTERS =========================

    public Long getId() {
        return id;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
}
