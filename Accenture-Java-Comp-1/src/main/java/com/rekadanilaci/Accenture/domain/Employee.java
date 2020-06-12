package com.rekadanilaci.Accenture.domain;

import com.rekadanilaci.Accenture.dto.EmployeeDto;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Employee(EmployeeDto employeeDto) {
        this.name = employeeDto.getName();
    }
}
