package com.corpguard.service.passmanagement.repo.employee;

import com.corpguard.service.passmanagement.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Add custom queries if needed
}
