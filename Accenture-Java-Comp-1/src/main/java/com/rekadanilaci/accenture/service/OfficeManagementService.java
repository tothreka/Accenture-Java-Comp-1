package com.rekadanilaci.accenture.service;

import com.rekadanilaci.accenture.domain.DailyList;
import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.domain.Office;
import com.rekadanilaci.accenture.domain.Reservation;
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
    //private Office office = Office.getInstance();
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
            }
        }
        return state;
    }

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

    public boolean requestEntryToOffice(Long employeeId) {
        fetchDailyListFromService();
        boolean entered = dailyListService.enterOffice(employeeId);
        return entered;
    }


    public void exitOffice(Long employeeId) {
        fetchDailyListFromService();
        dailyListService.exitOffice(employeeId);

    }

    private void fetchDailyListFromService() {
        LocalDate day = LocalDate.now();
        DailyList dailyList = office.getReservationsLists().get(day);
        dailyListService.setDailyList(dailyList);
    }

    public void fillEmployees() {
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setName("Employee" + i);
            office.addEmployee(employee);
            employeeRepository.save(employee);
        }
        logger.info("Added " + office.getStaff().size() + " employees to database");
    }


    //============ HELPER METHODS ==========

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
        boolean exists = reservationList.contains(reservationToSearch);
        if (exists) {
            logger.info("Your reservation was not created, you already have reservations for this day.");
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


    private Reservation connectDailyListAndReservation(LocalDate day, Reservation reservation) {
        DailyList dailyList = office.getReservationsLists().get(day);
        dailyListService.setDailyList(dailyList);
        dailyListService.addReservation(reservation);
        reservation.setDailyList(dailyList);
        Reservation successfulReservation = reservationRepository.save(reservation);
        return successfulReservation;
    }


    private String checkIfEmployeeIsValid(Long employeeID, String reservationStatus) {
        Employee employee = findEmployeeInStaff(employeeID);
        if (employee == null) {
            reservationStatus = "Invalid ID, we can not present a valid status.";
        }
        return reservationStatus;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }
}
