package com.corpguard.service.passmanagement.accesscard.entity;

import jakarta.persistence.*;

@Entity
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(nullable = false)
    private Boolean active = false;

    @Column(nullable = false)
    private Boolean issues = false;

    @OneToMany(mappedBy = "accessCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeAccessCard> employeeAccessCards;

    // Getters and Setters
    // toString, equals, and hashCode
}
