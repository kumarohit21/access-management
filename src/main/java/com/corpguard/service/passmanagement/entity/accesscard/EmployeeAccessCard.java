package com.corpguard.service.passmanagement.entity.accesscard;

import com.corpguard.service.passmanagement.entity.employee.Employee;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public Integer getRecordId() {
        return recordId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public LocalDateTime getIssuedDt() {
        return issuedDt;
    }

    public LocalDateTime getReturnDt() {
        return returnDt;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setAccessCard(AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    public void setIssuedDt(LocalDateTime issuedDt) {
        this.issuedDt = issuedDt;
    }

    public void setReturnDt(LocalDateTime returnDt) {
        this.returnDt = returnDt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EmployeeAccessCard that)) return false;
        return Objects.equals(recordId, that.recordId) && Objects.equals(employee, that.employee) && Objects.equals(accessCard, that.accessCard) && Objects.equals(issuedDt, that.issuedDt) && Objects.equals(returnDt, that.returnDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, employee, accessCard, issuedDt, returnDt);
    }

    @Override
    public String toString() {
        return "EmployeeAccessCard{" +
                "recordId=" + recordId +
                ", employee=" + employee +
                ", accessCard=" + accessCard +
                ", issuedDt=" + issuedDt +
                ", returnDt=" + returnDt +
                '}';
    }
}
