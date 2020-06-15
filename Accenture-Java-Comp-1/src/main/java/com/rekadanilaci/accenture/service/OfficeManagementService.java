package com.rekadanilaci.accenture.service;

import com.rekadanilaci.accenture.domain.*;
import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.repository.DailyListRepository;
import com.rekadanilaci.accenture.repository.EmployeeRepository;
import com.rekadanilaci.accenture.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OfficeManagementService {
    private static final Logger logger = LoggerFactory.getLogger(OfficeManagementService.class);
    private DailyListRepository dailyListRepository;
    private Office office;
    private DailyListManagementService dailyListService;
    private ReservationRepository reservationRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public OfficeManagementService(DailyListRepository dailyListRepository, Office office, DailyListManagementService dailyListService, ReservationRepository reservationRepository, EmployeeRepository employeeRepository) {
        this.dailyListRepository = dailyListRepository;
        this.office = office;
        this.dailyListService = dailyListService;
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
    }

    public OfficeManagementService() {
    }

    //========================= REGISTER ENDPOINT ================================

    /**
     * When an employee starts to create a reservation, the below method manages
     * the request and sends back an information message to the employee.
     * Only one active reservation is allowed for a given day from an employee.
     * @param reservationDto built based on the request
     *                       containing employeeID and requested date
     * @return understandable message to the employee
     */

    public String createNewReservation(ReservationDto reservationDto) {
        String state = "";
        Long employeeId = reservationDto.getPerson();
        LocalDate day = reservationDto.getDay();
        Employee employee = findEmployeeInStaff(employeeId);
        state = "employee found " + employee.getName();
        if (validateReservationRequest(employee, day)) {
            state = "Valid reservation";
            if (!checkForAlreadyExistingReservation(day, employee)) {
                state = "Reservation does not exist";
                Reservation reservation = new Reservation(employee, day);

                if (connectDailyListAndReservation(day, reservation) != null) {
                    state = "Created";
                } else {
                    state = "Reservation creation error";
                }
            } else {
                state = "Reservation already exist";
            }
        } else {
            state = "Invalid reservation";
        }
        return state;
    }

    public boolean validateReservationRequest(Employee employee, LocalDate day) {
        boolean valid = true;
        if (employee == null) {
            logger.warn("Invalid ID, no reservation was created.");
            valid = false;
        }
        if (day.isBefore(LocalDate.now())) {
            logger.warn("Invalid day, no reservation was created.");
            valid = false;
        }
        //Assumption: Weekend days are valid as well.
        return valid;
    }

    private boolean checkForAlreadyExistingReservation(LocalDate day, Employee employee) {
        DailyList dailyList = createDailyListIfMissing(day);
        Reservation reservationToSearch = new Reservation(employee, day);
        List<Reservation> reservationList = dailyList.getReservationList();
        reservationList.contains(reservationToSearch);
        boolean exists = false;
        for (Reservation reservation : reservationList) {
            if (reservation.getEmployee().equals(employee) && !reservation.getReservationStatus().equals(ReservationStatus.LEFT_OFFICE)) {
                exists = true;
                logger.info("Your reservation was not created, you already have reservations for this day.");

            }
        }
        return exists;
    }

    private DailyList createDailyListIfMissing(LocalDate day) {
        Map<LocalDate, DailyList> reservationsLists = office.getReservationsLists();
        if (!reservationsLists.containsKey(day)) {
            DailyList dailyList = new DailyList();
            reservationsLists.put(day, dailyList);
            dailyListRepository.save(dailyList);
            return dailyList;
        }
        return reservationsLists.get(day);
    }

    private Reservation connectDailyListAndReservation(LocalDate day, Reservation reservation) {
        DailyList dailyList = office.getReservationsLists().get(day);
        dailyListService.setDailyList(dailyList);
        dailyListService.addReservation(reservation);
        reservation.setDailyList(dailyList);
        Reservation successfulReservation = reservationRepository.save(reservation);
        return successfulReservation;
    }

    //========================= STATUS ENDPOINT ================================

    /**
     * Based on a request created in the frontend the application can send the
     * appropriate message to the employee. If the employee can not enter the office
     * immediately, the position of the reservation in the waiting list will be
     * communicated as well.
     * Position 1 means: if somebody leaves the office, you can enter.
     * @param employeeID
     * @return status text
     */


    public String getReservationStatus(Long employeeID) {
        LocalDate day = LocalDate.now();
        Map<LocalDate, DailyList> reservationsLists = office.getReservationsLists();
        String reservationStatus = "";
        dailyListService.setDailyList(reservationsLists.get(day));
        Integer freePlaces = office.getFreePlaces();

        if (!reservationsLists.containsKey(day)) {
            reservationStatus = "There are no reservations for today.";
        } else {
            int position = dailyListService.getPositionInEnrollList(employeeID, freePlaces);
            reservationStatus = position < 0 ? "You can enter the office, there is free place." :
                    "You are in the position " + (position + 1) + " now in the waiting list for the given day.";
        }
        reservationStatus = checkIfEmployeeIsValid(employeeID, reservationStatus);
        return reservationStatus;
    }

    private String checkIfEmployeeIsValid(Long employeeID, String reservationStatus) {
        Employee employee = findEmployeeInStaff(employeeID);
        if (employee == null) {
            reservationStatus = "Invalid ID, we can not present a valid status.";
        }
        return reservationStatus;
    }

    public Employee findEmployeeInStaff(Long employeeID) {
        Employee tempEmp = null;
        List<Employee> staff = office.getStaff();
        if (staff.size() > 0) {
            for (Employee employee : staff) {
                boolean matches = employee.getId().equals(employeeID);
                if (matches) {
                    tempEmp = employee;
                }
            }
        } else {
            logger.warn("No employee in office staff");
        }
        return tempEmp;
    }



    //========================= ENTRY ENDPOINT ================================

    /**
     * If an employee wants to enter, the application will check the validity
     * of the request.
     * @param employeeId
     * @return
     */

    public boolean requestEntryToOffice(Long employeeId) {
        fetchDailyListFromService();
        boolean entered = dailyListService.enterOffice(employeeId);
        return entered;
    }



    //========================= EXIT ENDPOINT ================================

    /**
     * If an employee wants to exit, the application will always allow it.
     * @param employeeId
     */

    public boolean exitOffice(Long employeeId) {
        fetchDailyListFromService();
        boolean exited = dailyListService.exitOffice(employeeId);
        return exited;
    }

    private void fetchDailyListFromService() {
        LocalDate day = LocalDate.now();
        DailyList dailyList = office.getReservationsLists().get(day);
        dailyListService.setDailyList(dailyList);
    }

    //========================= OTHER METHODS ================================

    public void fillEmployees() {
        //for test purposes
        StringBuilder employeeName = new StringBuilder();
        for (int i = 1; i < 301; i++) {
            employeeName.append("Employee");
            employeeName.append(1);
            String name = employeeName.toString();
            Employee employee = new Employee(name);
            office.addEmployee(employee);
            employeeRepository.save(employee);
            employeeName.delete(0, employeeName.length());
        }
        logger.info("Added " + office.getStaff().size() + " employees to database");
    }


    //========================= GETTERS ================================

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public Office getOffice() {
        return office;
    }
}
