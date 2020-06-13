package com.rekadanilaci.accenture.service;

import com.rekadanilaci.accenture.domain.DailyList;
import com.rekadanilaci.accenture.domain.Reservation;
import com.rekadanilaci.accenture.domain.ReservationStatus;
import com.rekadanilaci.accenture.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyListManagementService {
    private DailyList dailyList;

    @Autowired
    public DailyListManagementService(DailyList dailyList, ReservationRepository reservationRepository) {
        this.dailyList = dailyList;
    }


    public int getPositionInEnrollList(Long employeeId, int officeFreePlaces) {
        List<Reservation> reservationList = dailyList.getReservationList();
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
        List<Reservation> reservationList = dailyList.getReservationList();
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

    public boolean enterOffice(Long employeeId) {
        boolean entered = false;
        List<Reservation> reservationList = dailyList.getReservationList();
        for (Reservation reservation : reservationList) {
            if (reservation.getEmployee().getId().equals(employeeId) &&
                    reservation.getReservationStatus().equals(ReservationStatus.ENROLLED)) {
                reservation.setReservationStatus(ReservationStatus.ENTERED_OFFICE);
                entered = true;
            }
        }
        return entered;
    }

    public void exitOffice(Long employeeId) {
        List<Reservation> reservationList = dailyList.getReservationList();
        for (Reservation reservation : reservationList) {
            if (reservation.getEmployee().getId().equals(employeeId) &&
                    reservation.getReservationStatus().equals(ReservationStatus.ENTERED_OFFICE)) {
                reservation.setReservationStatus(ReservationStatus.LEFT_OFFICE);
            }
        }
    }


    //============ GETTER, SETTER ============

    public DailyList getDailyList() {
        return dailyList;
    }

    public void setDailyList(DailyList dailyList) {
        this.dailyList = dailyList;
    }
}
