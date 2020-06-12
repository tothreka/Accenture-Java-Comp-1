package com.rekadanilaci.accenture.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Office {

    private Long id;
    private final Integer PLACES = 250;
    private Integer capacity;
    private Integer freePlaces;
    private List<Employee> staff;
    private Map <LocalDate, DailyList> reservationsLists;

    public Office() {
        this.capacity = 100;
        this.freePlaces = PLACES * capacity / 100;
        this.staff = new ArrayList<>();
        this.reservationsLists = new HashMap<>();
    }

    public String registerReservation(Long employeeID, LocalDate day) {
        Employee employee = findEmployeeInStaff(employeeID);
        if (employee == null) {
            return "Invalid ID, no reservation was created.";
        }
        if (day.compareTo(LocalDate.now()) < 0) {
            return "Invalid day, no reservation was created.";
        } //Assumption: Weekend days are valid as well.
        if (!reservationsLists.containsKey(day)) {
            reservationsLists.put(day, new DailyList());
        }
        reservationsLists.get(day).addReservation(new Reservation(employee, day));
        return "Your reservation was created.";
    }

    public Employee findEmployeeInStaff(Long employeeID) {
        for (Employee employee : staff) {
            if (employee.getId() == employeeID) {
                return employee;
            }
        }
        return null;
    }

    public void setNewCapacity(Integer capacity) {
        if (0 <= capacity && capacity <= 100) {
            this.capacity = capacity;
        } else {

        }
    };

    // =========== GETTERS =============

    public Integer getPLACES() {
        return PLACES;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getFreePlaces() {
        return freePlaces;
    }

    public List<Employee> getStaff() {
        return staff;
    }

    public Map<LocalDate, DailyList> getReservationsLists() {
        return reservationsLists;
    }

}
