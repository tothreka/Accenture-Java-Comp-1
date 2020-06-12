package com.rekadanilaci.accenture.domain;

import com.rekadanilaci.accenture.dto.EmployeeDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;

    public Employee(EmployeeDto employeeDto) {
        this.name = employeeDto.getName();
    }

    public Employee(String name) {
        //For test purposes
        this.name = name;
    }

    public Employee() {
    }

    // ================= GETTERS ======================

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
}
