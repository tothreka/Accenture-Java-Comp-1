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
 * the DailyLists contain the Reservations in order of creations.
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


    //========================= GETTERS =========================

    public Long getId() {
        return id;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
}
