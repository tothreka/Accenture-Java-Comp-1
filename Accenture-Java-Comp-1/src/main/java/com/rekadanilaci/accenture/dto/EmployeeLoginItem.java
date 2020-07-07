package com.rekadanilaci.accenture.dto;

import com.rekadanilaci.accenture.domain.Employee;

public class EmployeeLoginItem {
    private Long id;
    private String password;

    public EmployeeLoginItem(Employee employee) {
        this.id = employee.getId();
        this.password = employee.getPassword();
    }

    public EmployeeLoginItem() {
    }

    public Long getId() {
        return id;
    }
}
