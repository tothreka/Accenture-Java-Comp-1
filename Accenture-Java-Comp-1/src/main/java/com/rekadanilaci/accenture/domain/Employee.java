package com.rekadanilaci.accenture.domain;

import javax.persistence.*;

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

    @Column(name = "password")
    private String password;

    @ManyToOne
    private Admin admin;


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


}
