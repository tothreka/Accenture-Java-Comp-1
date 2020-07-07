package com.rekadanilaci.accenture.dto;

import com.rekadanilaci.accenture.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataItem {
    private Long id;
    private String name;
    private String password;
    private List<ReservationDataItem> reservationList = new ArrayList<>();

    public EmployeeDataItem(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.password = employee.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<ReservationDataItem> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<ReservationDataItem> reservationList) {
        this.reservationList = reservationList;
    }
}
