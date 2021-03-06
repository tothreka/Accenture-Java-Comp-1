package com.rekadanilaci.accenture;

import com.rekadanilaci.accenture.dto.ReservationItem;
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
        ReservationItem reservationItem = new ReservationItem(employeeId, today);
        String saveMessage = officeManagementService.createNewReservation(reservationItem);
        Assertions.assertEquals("Created", saveMessage);
        Assertions.assertEquals(1, officeManagementService.getOffice().getReservationsLists().size());
        officeManagementService.getOffice().getReservationsLists().clear();
    }

    @Test
    public void newInvalidReservationForYesterdayTest() {
        LocalDate yesterday = today.minusDays(1);
        Long employeeId = officeManagementService.getOffice().getStaff().get(15).getId();
        ReservationItem reservationItem = new ReservationItem(employeeId, yesterday);
        String saveMessage = officeManagementService.createNewReservation(reservationItem);
        Assertions.assertEquals("Invalid reservation", saveMessage);
        Assertions.assertEquals(0, officeManagementService.getOffice().getReservationsLists().size());
    }

    @Test
    public void newInvalidReservationForAlreadyExistingTest() {
        Long employeeId = officeManagementService.getOffice().getStaff().get(15).getId();
        ReservationItem reservationItem = new ReservationItem(employeeId, today);
        ReservationItem reservationItemDoubled = new ReservationItem(employeeId, today);
        officeManagementService.createNewReservation(reservationItem);
        String saveMessage = officeManagementService.createNewReservation(reservationItemDoubled);
        Assertions.assertEquals("Reservation already exist", saveMessage);
        Assertions.assertEquals(1, officeManagementService.getOffice().getReservationsLists().size());
        officeManagementService.getOffice().getReservationsLists().clear();
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
        ReservationItem reservationItem = new ReservationItem(employeeId, today);
        officeManagementService.createNewReservation(reservationItem);
        Assertions.assertEquals("You can enter the office, there is free place.", officeManagementService.getReservationStatus(employeeId));
        officeManagementService.getOffice().getReservationsLists().clear();
    }

    @Test
    public void getStatusEnrolledTest() {
        officeManagementService.getOffice().setNewCapacity(2);
        ReservationItem reservationItem;
        for (int i = 0; i < 6; i++) {
            reservationItem = new ReservationItem(officeManagementService.getOffice().getStaff().get(i).getId(), today);
            officeManagementService.createNewReservation(reservationItem);
        }
        Assertions.assertEquals("You are in the position 1 now in the waiting list for the given day.", officeManagementService.getReservationStatus(officeManagementService.getOffice().getStaff().get(5).getId()));
        officeManagementService.getOffice().getReservationsLists().clear();
    }

   /* @Test
    public void enterOfficeTest() {
        List<Employee> staff = officeManagementService.getOffice().getStaff();
        Long employeeId = staff.get(0).getId();
        ReservationItem reservationItem = new ReservationItem(employeeId, today);
        officeManagementService.createNewReservation(reservationItem);
        Assertions.assertTrue(officeManagementService.requestEntryToOffice(employeeId));

        Reservation reservation = officeManagementService.getOffice().getReservationsLists().get(LocalDate.now())
                .getReservationList().get(0);

        Assertions.assertEquals(ReservationStatus.ENTERED_OFFICE, reservation.getReservationStatus());
        officeManagementService.getOffice().getReservationsLists().clear();
    }


    @Test
    public void leftOfficeTest() {
        Long employeeId = officeManagementService.getOffice().getStaff().get(0).getId();
        ReservationItem reservationItem = new ReservationItem(employeeId, today);
        officeManagementService.createNewReservation(reservationItem);
        Reservation reservation = officeManagementService.getOffice().getReservationsLists().get(LocalDate.now())
                .getReservationList().get(0);
        officeManagementService.createNewReservation(reservationItem);
        officeManagementService.requestEntryToOffice(employeeId);
        officeManagementService.exitOffice(employeeId);
        Assertions.assertEquals(ReservationStatus.LEFT_OFFICE, reservation.getReservationStatus());
        officeManagementService.getOffice().getReservationsLists().clear();
    }
*/


}
