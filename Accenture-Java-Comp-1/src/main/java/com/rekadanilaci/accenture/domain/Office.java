package com.rekadanilaci.accenture.domain;

import com.rekadanilaci.accenture.repository.DailyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The task description mentioned only one Office, thus, this class supposed to be a
 * singleton in the current version. It can be extended for a multi-office version
 * after introducing an ID field.
 */

@Component
public class Office {

    private DailyListRepository dailyListRepository;

    /**
     * The maximum number of available places if the office works with full capacity.
     * It is a constant according to the task description.
     */
    private final Integer PLACES = 250;

    /**
     * The actually allowed capacity in %. It can be changed easily to increase the
     * targeted occupation level.
     */
    private Integer capacity = 100;

    /**
     * The number of the usable places depending on the allowed capacity %.
     */
    private Integer freePlaces = PLACES * capacity / 100;

    /**
     * List of all Employees, who can enter the office.
     */
    private List<Employee> staff = new ArrayList<>();

    /**
     * Lists of Reservations mapped by the targeted day.
     */
    private Map<LocalDate, DailyList> reservationsLists = new HashMap<>();

    @Autowired
    public Office(DailyListRepository dailyListRepository) {
        this.dailyListRepository = dailyListRepository;
    }

    public Office() {
    }


    // =========================== GENERAL METHODS ==================================

    public void setNewCapacity(Integer capacity) {
        if (0 <= capacity && capacity <= 100) {
            this.capacity = capacity;
            this.freePlaces = PLACES * capacity / 100;
        } else {
            System.out.println("Capacity must be an integer between 0 and 100.");
        }
    }

    public void addEmployee(Employee employee) {
        this.staff.add(employee);
    }

    // ================================ GETTERS =====================================

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
