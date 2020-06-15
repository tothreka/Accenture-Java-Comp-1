package com.rekadanilaci.accenture.domain;

import com.rekadanilaci.accenture.dto.EmployeeDto;

import javax.persistence.*;
import java.util.List;

/**
 * We assumed that only employees can enter the office in working hours,
 * thus, only these objects (Employees) must be handled by the application
 * as potential users of the office.
 *
 * The task description did not define the needed fields, thus, we tried to
 * keep the things as simple as possible, and only one field (name) was
 * introduced. It must contain first name + last name as well. The list
 * of fields can be extended later if needed.
 *
 * ID will be given to the employees by the application itself automatically,
 * one ID will be never used again. Thus, the application can be used
 * for retrospective backtracking as well.
 *
 * Important : The entry cards must know the ID of their owners. The base of
 * the identification is the ID, not the name. (The same name can occur
 * many times at bigger companies.)
 */

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

    public Employee(String name, List<Reservation> reservationList) {
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

    // ================= SETTERS ======================


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
