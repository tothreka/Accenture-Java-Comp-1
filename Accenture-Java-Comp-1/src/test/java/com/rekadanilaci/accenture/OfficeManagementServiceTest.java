package com.rekadanilaci.accenture;

import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.domain.Reservation;
import com.rekadanilaci.accenture.domain.ReservationStatus;
import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.service.OfficeManagementService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback
class OfficeManagementServiceTest {

    LocalDate today = LocalDate.now();

    @Autowired
    private OfficeManagementService officeManagementService;

    @BeforeEach
    public void makeEmployees() {
        officeManagementService.fillEmployees();
        officeManagementService.getOffice().setNewCapacity(10);
    }

    @AfterEach
    public void deleteEmployees() {
        officeManagementService.getOffice().getStaff().clear();
        officeManagementService.getEmployeeRepository().deleteAll();
    }

    @Test
    public void makeEmployeesTest() {
        Assertions.assertEquals(300, officeManagementService.getOffice().getStaff().size());
    }

    @Test
    public void newGoodReservationTest() {
        Long employeeId = officeManagementService.getOffice().getStaff().get(15).getId();
        ReservationDto reservationDto = new ReservationDto(employeeId, today);
        String saveMessage = officeManagementService.createNewReservation(reservationDto);
        Assertions.assertEquals("Created", saveMessage);
        Assertions.assertEquals(1, officeManagementService.getOffice().getReservationsLists().size());
        officeManagementService.getOffice().getReservationsLists().clear();
    }

    @Test
    public void newInvalidReservationTest() {
        LocalDate yesterday = today.minusDays(1);
        Long employeeId = officeManagementService.getOffice().getStaff().get(15).getId();
        ReservationDto reservationDto = new ReservationDto(employeeId, yesterday);
        String saveMessage = officeManagementService.createNewReservation(reservationDto);
        Assertions.assertEquals("Invalid reservation", saveMessage);
        Assertions.assertEquals(0, officeManagementService.getOffice().getReservationsLists().size());
    }

    @Test
    public void setValidOfficeCapacity() {
        Assertions.assertEquals(25, officeManagementService.getOffice().getFreePlaces());
        officeManagementService.getOffice().setNewCapacity(50);
        Assertions.assertEquals(125, officeManagementService.getOffice().getFreePlaces());
    }

    @Test
    public void getStatusCanEnterTest() {
        Long employeeId = officeManagementService.getOffice().getStaff().get(0).getId();
        ReservationDto reservationDto = new ReservationDto(employeeId, today);
        officeManagementService.createNewReservation(reservationDto);
        Assertions.assertEquals("You can enter the office, there is free place.", officeManagementService.getReservationStatus(employeeId));
        officeManagementService.getOffice().getReservationsLists().clear();
    }

    @Test
    public void getStatusEnrolledTest() {
        officeManagementService.getOffice().setNewCapacity(2);
        ReservationDto reservationDto;
        for (int i = 0; i < 6; i++) {
            reservationDto = new ReservationDto(officeManagementService.getOffice().getStaff().get(i).getId(), today);
            officeManagementService.createNewReservation(reservationDto);
        }
        Assertions.assertEquals("You are in the position 1 now in the waiting list for the given day.", officeManagementService.getReservationStatus(officeManagementService.getOffice().getStaff().get(5).getId()));
        officeManagementService.getOffice().getReservationsLists().clear();
    }

    @Test
    public void enterOfficeTest() {
        List<Employee> staff = officeManagementService.getOffice().getStaff();
        Long employeeId = staff.get(0).getId();
        ReservationDto reservationDto = new ReservationDto(employeeId, today);
        officeManagementService.createNewReservation(reservationDto);
        Assertions.assertTrue(officeManagementService.requestEntryToOffice(employeeId));

        Reservation reservation = officeManagementService.getOffice().getReservationsLists().get(LocalDate.now())
                .getReservationList().get(0);

        Assertions.assertEquals(ReservationStatus.ENTERED_OFFICE, reservation.getReservationStatus());
        officeManagementService.getOffice().getReservationsLists().clear();
    }



}
