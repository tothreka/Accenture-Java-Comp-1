package com.rekadanilaci.accenture;

import com.rekadanilaci.accenture.domain.Employee;
import com.rekadanilaci.accenture.domain.Office;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@SpringBootTest
class AllTest {

	private Office office = new Office();

	@BeforeTestClass
	void makeEmployees() {
		office.getStaff().clear();
		for (int i = 1; i <= 300; i++) {
			office.getStaff().add(new Employee("Employee" + i));
		}
		office.setNewCapacity(10);
	}

	@BeforeEach
	void setCapacityBackTo10() {
		office.setNewCapacity(10);
	}

	@Test
	void testSetNewCapacity() {
		office.setNewCapacity(50);
		Assertions.assertEquals(50, office.getCapacity());
	}

	@Test
	void testFreeRoomWithIncreasedCapacity() {
		office.setNewCapacity(50);
		Assertions.assertEquals(125, office.getFreePlaces());
	}

}
