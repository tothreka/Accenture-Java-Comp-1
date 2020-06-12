package com.rekadanilaci.Accenture.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Office {

    private Integer PLACES;
    private Integer capacity;
    private Integer freePlaces;
    private List<Employee> staff;
    private Map <LocalDate, List<Reservation>>;

    public Office(Integer PLACES, Integer capacity, Integer freePlaces, List<Employee> staff) {
        this.PLACES = PLACES;
        this.capacity = capacity;
        this.freePlaces = freePlaces;
        this.staff = staff;
    }
}
