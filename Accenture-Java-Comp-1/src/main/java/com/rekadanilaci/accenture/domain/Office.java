package com.rekadanilaci.accenture.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Office {

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

    // ========== REGISTER ENDPOINT ===========

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
        if (reservationsLists.get(day).addReservation(new Reservation(employee, day))) {
            return "Your reservation was created.";
        } else {
            return "Your reservation was not created, you have already reservations for this day.";
        }
    }

    public Employee findEmployeeInStaff(Long employeeID) {
        for (Employee employee : staff) {
            if (employee.getId().equals(employeeID)) {
                return employee;
            }
        }
        return null;
    }

    // =========== STATUS ENDPOINT ===========

    public String reportStatus (Long employeeID, LocalDate day) {
        String answer = "";
        if (!reservationsLists.containsKey(day)) {
            answer = "There are no reservations for this day.";
        } else {
            int position = reservationsLists.get(day).getPositionInEnrollList(employeeID, freePlaces);
            answer = position < 0 ? "You can enter the office, there is free place." :
                    "You are in the position " + (position + 1) + " now in the waiting list for the given day.";
        }
        Employee employee = findEmployeeInStaff(employeeID);
        if (employee == null) {
            answer = "Invalid ID, we can not present a valid status.";
        }
        return answer;
    }

    // =========== ENTRY ENDPOINT ===========

    public boolean requestEntry (Long employeeID) {
        DailyList todayList = reservationsLists.get(LocalDate.now());
        if (todayList == null || todayList.getPositionInEnrollList(employeeID, freePlaces) >= 0) {
            return false;
        } else {
            todayList.enterOffice(employeeID);
            return true;
        }
    }

    // =========== EXIT ENDPOINT ===========

    public void exit(Long employeeID) {
        DailyList todayList = reservationsLists.get(LocalDate.now());
        todayList.exitOffice(employeeID);
    }




    // =========== GENERAL METHODS ===========

    public void setNewCapacity(Integer capacity) {
        if (0 <= capacity && capacity <= 100) {
            this.capacity = capacity;
            this.freePlaces = PLACES * capacity / 100;
        } else {
            System.out.println("Capacity must be an integer between 0 and 100.");
        }
    }

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
