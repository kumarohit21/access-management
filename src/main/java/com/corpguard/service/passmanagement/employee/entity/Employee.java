package com.corpguard.service.passmanagement.employee.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @Column(nullable = false, length = 100)
    private String empName;

    @Column(nullable = false, length = 100)
    private String empDept;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeAccessCard> accessCards;

    // Getters and Setters
    // toString, equals, and hashCode
}
