package com.rekadanilaci.accenture.dto;

import com.rekadanilaci.accenture.domain.Employee;

public class EmployeeDataItem {
    private Long id;
    private String name;
    private String password;

    public EmployeeDataItem(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.password = employee.getPassword();
    }
}
