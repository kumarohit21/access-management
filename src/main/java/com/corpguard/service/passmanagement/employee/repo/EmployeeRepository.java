package com.corpguard.service.passmanagement.employee.repo;

import com.corpguard.service.passmanagement.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Add custom queries if needed
}
