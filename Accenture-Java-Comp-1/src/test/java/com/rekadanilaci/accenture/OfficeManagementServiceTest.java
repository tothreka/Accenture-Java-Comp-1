package com.rekadanilaci.accenture;

import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.domain.Office;
import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.service.OfficeManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest
@Transactional
@Rollback
class OfficeManagementServiceTest {

    @Autowired
    private OfficeManagementService officeManagementService;

    @BeforeTestClass
    public void makeEmployees() {
        officeManagementService.fillEmployees();
    }

    @Test
    public void makeEmployeesTest() {
        Assertions.assertEquals(300, officeManagementService.getOffice().getStaff().size());
    }

    @Test
    public void newReservationGood() {
        Long employeeId = officeManagementService.getOffice().getStaff().get(15).getId();
        ReservationDto reservationDto = new ReservationDto(employeeId, LocalDate.now());
        String saveMessage = officeManagementService.createNewReservation(reservationDto);
        Assertions.assertEquals("Created", saveMessage);
        Assertions.assertEquals(1, officeManagementService.getOffice().getReservationsLists().size());
    }




}
