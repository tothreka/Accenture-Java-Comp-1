package com.rekadanilaci.accenture;

import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.domain.Office;
import com.rekadanilaci.accenture.dto.ReservationDto;
import com.rekadanilaci.accenture.service.OfficeManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class AllTest {

    private Office office = Office.getInstance();
    @Autowired
    private OfficeManagementService officeManagementService;

    @BeforeEach
    void makeEmployees() {
        office.getStaff().clear();
        for (int i = 1; i <= 300; i++) {
            Employee employee = new Employee();
            employee.setId((long) i);
            employee.setName("Employee" + i);
            office.addEmployee(employee);
        }
        office.setNewCapacity(10);
    }

    @Test
    void testSetNewCapacity() {
        office.setNewCapacity(50);
        Assertions.assertEquals(50, office.getCapacity());
    }

    @Test
    void testFreeRoomWithIncreasedCapacity() {
        Assertions.assertEquals(25, office.getFreePlaces());
        office.setNewCapacity(50);
        Assertions.assertEquals(125, office.getFreePlaces());
    }

    //TODO ez vmiért enm fut le, de élesben meg lefut
    @Test
    void registerGoodReservation() {
        Long employeeId = office.getStaff().get(10).getId();
        LocalDate date = LocalDate.now();
        ReservationDto dto = new ReservationDto(employeeId, date);
        officeManagementService.createNewReservation(dto);
        Assertions.assertEquals("Created", officeManagementService.createNewReservation(dto));

    }


}
