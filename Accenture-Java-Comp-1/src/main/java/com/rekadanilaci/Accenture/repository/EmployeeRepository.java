package com.rekadanilaci.Accenture.repository;

import com.rekadanilaci.Accenture.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
