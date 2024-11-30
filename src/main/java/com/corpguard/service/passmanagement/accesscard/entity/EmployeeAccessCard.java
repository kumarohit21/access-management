package com.corpguard.service.passmanagement.accesscard.entity;

import com.corpguard.service.passmanagement.employee.entity.Employee;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmployeeAccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private AccessCard accessCard;

    @Column(nullable = false)
    private LocalDateTime issuedDt;

    @Column
    private LocalDateTime returnDt;

    // Getters and Setters
    // toString, equals, and hashCode
}
