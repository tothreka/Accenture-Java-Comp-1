package com.rekadanilaci.accenture.dto;

import com.rekadanilaci.accenture.domain.Admin;
import com.rekadanilaci.accenture.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class AdminDataItem {
    private Long id;
    private String name;
    private List<EmployeeDataItem> employeeList = new ArrayList<>();

    public AdminDataItem(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        for (Employee managedEmployee : admin.getManagedEmployees()) {
            EmployeeDataItem employeeDataItem = new EmployeeDataItem(managedEmployee);
            this.employeeList.add(employeeDataItem);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDataItem> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDataItem> employeeList) {
        this.employeeList = employeeList;
    }
}
