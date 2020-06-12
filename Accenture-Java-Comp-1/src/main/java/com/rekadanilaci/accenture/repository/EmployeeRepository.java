package com.rekadanilaci.accenture.repository;

import com.rekadanilaci.accenture.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
