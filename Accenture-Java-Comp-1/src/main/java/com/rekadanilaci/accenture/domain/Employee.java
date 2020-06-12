package com.rekadanilaci.accenture.domain;

import com.rekadanilaci.accenture.dto.EmployeeDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @Column(name = "reservations")
    private List<Reservation> reservationList;

    public Employee(EmployeeDto employeeDto) {
        this.name = employeeDto.getName();
    }

    public Employee() {
    }
}
