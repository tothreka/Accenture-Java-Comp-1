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

    public DailyList(Reservation reservation) {
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation);
        this.reservationList = reservationList;
    }

    public DailyList() {
    }

    public Long getId() {
        return id;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
}
