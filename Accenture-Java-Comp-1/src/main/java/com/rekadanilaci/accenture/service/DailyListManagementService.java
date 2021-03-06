package com.rekadanilaci.accenture.service;

import com.rekadanilaci.accenture.domain.DailyList;
import com.rekadanilaci.accenture.domain.Reservation;
import com.rekadanilaci.accenture.domain.ReservationStatus;
import com.rekadanilaci.accenture.dto.ReservationDataItem;
import com.rekadanilaci.accenture.repository.DailyListRepository;
import com.rekadanilaci.accenture.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyListManagementService {
    private DailyList dailyList;
    private DailyListRepository dailyListRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public DailyListManagementService(DailyList dailyList, DailyListRepository dailyListRepository, ReservationRepository reservationRepository) {
        this.dailyList = dailyList;
        this.dailyListRepository = dailyListRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     * The below method calculates the position of the employee in the actual waiting list
     * (calculated from the appropriate dailylist, that contains all Reservations regardless
     * of their status.)
     * The parameter "officeFreePlaces" will be transferred from the Office class to get
     * the actual number of places ready to use.
     * Negative result means, the employee can  enter the office immediately.
     *
     * @param employeeId
     * @param officeFreePlaces
     * @return
     */

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

    /**
     * Reservations will be added to the appropriate dailyLists immediately after creation.
     * If the employee has an active Reservation for the given day, no Reservation will be
     * created.
     *
     * @param reservation
     * @return
     */

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

    /**
     * Employees can enter the office only if they have an active Reservation for the
     * given day. (Active = their status is ENROLLED.)
     *
     //  * @param employeeId
     * @return
     */

    public boolean enterOffice(ReservationDataItem reservationDataItem, Long reservationId) {
        boolean entered = false;

        Reservation reservationToChange = reservationRepository.getOne(reservationId);
        if (reservationToChange.getReservationStatus().equals(ReservationStatus.ENROLLED)) {
            reservationToChange.setReservationStatus(ReservationStatus.valueOf(reservationDataItem.getReservationStatus()));
            entered = true;
        }
        return entered;
    }

    /**
     * Exit is always allowed and executed.
     *
     * @param employeeId
     */

    public boolean exitOffice(ReservationDataItem reservationDataItem, Long reservationId) {
        boolean left = false;
        Reservation reservationToChange = reservationRepository.getOne(reservationId);
        if (reservationToChange.getReservationStatus().equals(ReservationStatus.ENTERED_OFFICE)) {
            reservationToChange.setReservationStatus(ReservationStatus.valueOf(reservationDataItem.getReservationStatus()));
            left = true;
        }
        return left;
    }


    //=========================== GETTER, SETTER ==================================

    public DailyList getDailyList() {
        return dailyList;
    }

    public void setDailyList(DailyList dailyList) {
        this.dailyList = dailyList;
    }
}
