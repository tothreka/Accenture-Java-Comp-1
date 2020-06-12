package com.rekadanilaci.accenture.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dailyList")
public class DailyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "dailyList", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;


    public DailyList() {
    }

    public int getPositionInEnrollList(Long employeeId, int officeFreePlaces) {
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
    }


    //========================= GETTERS =========================

    public Long getId() {
        return id;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
}
