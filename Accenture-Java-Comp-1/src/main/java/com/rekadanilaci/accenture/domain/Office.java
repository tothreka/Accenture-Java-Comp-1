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
    private static Office office = new Office();
    private DailyListRepository dailyListRepository;

    /**
     * The maximum number of available places if it works with 100% capacity.
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
    private Office(DailyListRepository dailyListRepository) {
        this.dailyListRepository = dailyListRepository;
    }

    private Office() {
    }

    public static Office getInstance() {
        return office;
    }

    // ========== REGISTER ENDPOINT ===========

    /*public ReservationMessage registerReservation(Long employeeID, LocalDate day) {
        Employee employee = findEmployeeInStaff(employeeID);
        if (employee == null) {
            logger.warn("Invalid ID, no reservation was created.");
            return ReservationMessage.INVALID_ID;
        }
        if (day.compareTo(LocalDate.now()) < 0) {
            logger.warn("Invalid day, no reservation was created.");
            return ReservationMessage.INVALID_DAY;
        } //Assumption: Weekend days are valid as well.
        if (!reservationsLists.containsKey(day)) {
            DailyList dailyList = new DailyList();
            dailyListRepository.save(dailyList);
            reservationsLists.put(day, dailyList);
        }
        if (reservationsLists.get(day).addReservation(new Reservation(employee, day))) {
            logger.info("Your reservation was created.");
            return ReservationMessage.CREATED;
        } else {
            logger.info("Your reservation was not created, you already have reservations for this day.");
            return ReservationMessage.ALREADY_CREATED;
        }
    }*/

    /*public Employee findEmployeeInStaff(Long employeeID) {
        for (Employee employee : staff) {
            if (employee.getId().equals(employeeID)) {
                return employee;
            }
        }
        return null;
    }*/

    //  =========== STATUS ENDPOINT ===========

    /*public String reportStatus(Long employeeID, LocalDate day) {
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

    public boolean requestEntry(Long employeeID) {
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
    }*/


    // =========== GENERAL METHODS ===========

    public void setNewCapacity(Integer capacity) {
        if (0 <= capacity && capacity <= 100) {
            this.capacity = capacity;
            this.freePlaces = PLACES * capacity / 100;
        } else {
            System.out.println("Capacity must be an integer between 0 and 100.");
        }
    }

    public void addEmployee(Employee employee) {
        //for test purposes
        this.staff.add(employee);
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
