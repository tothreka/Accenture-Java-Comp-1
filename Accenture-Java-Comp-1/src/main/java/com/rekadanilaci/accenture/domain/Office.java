package com.rekadanilaci.accenture.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Office {


    private Long id;

    private Integer PLACES;

    private Integer capacity;

    private Integer freePlaces;

    private List<Employee> staff;

    private Map <LocalDate, List<Reservation>> reservationsLists;

    public Office(Integer PLACES, Integer capacity) {
        this.PLACES = PLACES;
        this.capacity = capacity;
        this.freePlaces = PLACES * capacity / 100;
        this.staff = new ArrayList<>();
        this.reservationsLists = new HashMap<>();
    }

    public Office() {
    }
}
